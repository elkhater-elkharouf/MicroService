package com.example.produitcategorie.services;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CategoryStatisticsImageService {
    public static byte[] generateCategoryStatisticsImage(Map<String, Long> categoryStatistics) {
        // Créez un dataset de catégories basé sur les statistiques que vous avez obtenues
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Long> entry : categoryStatistics.entrySet()) {
            dataset.addValue(entry.getValue(), "Nombre de produits", entry.getKey());
        }

        // Créez un graphique à barres en utilisant le dataset
        JFreeChart chart = ChartFactory.createBarChart(
                "Statistiques des catégories",
                "Catégorie",
                "Nombre de produits",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Convertissez le graphique en un tableau de bytes (image).
        byte[] imageBytes = chartToImageBytes(chart);

        return imageBytes;
    }

    private static byte[] chartToImageBytes(JFreeChart chart) {
        try {
            BufferedImage image = chart.createBufferedImage(800, 600);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bos);
            return bos.toByteArray();
        } catch (IOException e) {
            // Gérez l'exception en fonction de vos besoins
            e.printStackTrace();
            return new byte[0]; // En cas d'erreur, retournez une image vide ou une image d'erreur
        }
    }
}
