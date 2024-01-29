package com.example.quiz.Controller;


import com.example.quiz.Entities.*;
import com.example.quiz.Repository.DomaineRepository;
import com.example.quiz.Repository.OptionRepository;
import com.example.quiz.Repository.QuestionRepository;
import com.example.quiz.Repository.UserRepository;
import com.example.quiz.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class formuContr {
   IQuestionService iQuestionService ;
   IReponseService iReponseService ;
   IOptionService iOptionService ;
   UserRepository userRepository ;
   IUserService iUserService ;
   IDomaineService iDomaineService ;
QuestionRepository questionRepository ;
OptionRepository optionRepository ;
DomaineRepository domaineRepository ;
QuestionService questionService ;
ReponseService reponseService ;

    @GetMapping("/admin/GetAllQuestions")
    public List<Question> getQuestions() {
        return iQuestionService.getAllQuestions();
    }


    @GetMapping("/user/GetAllQuestionsByDomaine/{domaineId}")
    public List<Question> getQuestions(@PathVariable("domaineId") long domaineId) {
        return iQuestionService.getAllQuestion(domaineId);
    }
   /* @PostMapping("/submit")
    public ResponseEntity<String> submitSecurityAnswers(@RequestBody List<SecurityAnswer> answers) {
        // Compare les réponses de l'utilisateur aux réponses attendues et calcule la note

        // Enregistrez les réponses de l'utilisateur
        for (SecurityAnswer answer : answers) {
            iSecurityQuestionService.saveSecurityAnswer(answer);
        }

        // Calculez la note et renvoyez-la en réponse
        // Vous pouvez implémenter cette logique ici

        return ResponseEntity.ok("Formulaire soumis avec succès. Votre note est : XX");
    }*/

    @PutMapping("/updateQuestion")
    public Question updateQuestion(@RequestBody Question q){
        return iQuestionService.updateQuestion(q);
    }
    @DeleteMapping("deleteQuestion/{idQuestion}")
    public String deleteQuestion(@PathVariable("idQuestion") long idQuestion){
        return iQuestionService.deleteQuestion(idQuestion) ;
    }
    @GetMapping("/user/getQuestionById/{idQuestion}")
    public Question getQuestionById(@PathVariable("idQuestion") long idQuestion){
        return iQuestionService.getById(idQuestion);
    }


    @PostMapping("admin/addquestion")
    public Question addQuestionWithOptions(@RequestBody Question question,@RequestParam long idDomaine) {

        return iQuestionService.addQuestionWithOptions(question,idDomaine);
    }
    @PostMapping("/admin/addOption/{idQuestion}")
    public Option addOptionAndAsseignToQuestion(@RequestBody Option o, @PathVariable("idQuestion") Long idQuestion){
        return iOptionService.addOptionAndAsseignToQuestion(o,idQuestion);

    }
@GetMapping("/user/getAllOption")
    public List<Option> GetAllOption() {
    return iOptionService.GetAllOption();
}
@PutMapping("/updateOption")
    public Option updateOption(@RequestBody Option option) {
        return iOptionService.updateOption(option);
}
    @DeleteMapping("deleteOption/{idOption}")
    public String deleteOption(@PathVariable("idOption") long idOption) {
        return iOptionService.deleteOption(idOption);
    }
    @GetMapping("/getOptionById/{idOption}")
    public Option getOptionById(@PathVariable("idOption") long idOption){
        return iOptionService.getById(idOption);
    }

    @GetMapping("getOptionsByQuestionId/{questionId}")
    public ResponseEntity<Set<Option>> getOptionsByQuestionId(@PathVariable Long questionId) {
        Set<Option> options = iQuestionService.getOptionsByQuestionId(questionId);
        return ResponseEntity.ok(options);
    }
    @GetMapping("/calculateScore/{domaineId}/{idUser}")
    public ResponseEntity<Integer> calculateDomainScore(@PathVariable Long domaineId,@PathVariable ("idUser")  long idUser) {
        Domaine domaine = domaineRepository.findById(domaineId).orElse(null);

        if (domaine == null) {
            return ResponseEntity.notFound().build();
        }

        int totalScore = questionService.calculateDomainScore(domaine ,idUser);
        return ResponseEntity.ok(totalScore);
    }
/*
    @PostMapping("/calculate/{userId}")
    public ResponseEntity<String> calculateScore(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            int score = (int) iUserService.calculateScore(user);
            return ResponseEntity.ok("Score calculated: " + score);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }*/
    @GetMapping("NivauxOfUser/{idUser}")
    public String NivauxOfUser (@PathVariable("idUser")Long idUser) {
        return iQuestionService.NivauxOfUser(idUser);
    }

    @PostMapping("addAnswer/{idQuestion}/{idUser}/{idOption}")
    public Reponse addAnswer(@RequestBody Reponse reponse ,@PathVariable("idQuestion")  long idQuestion,@PathVariable("idUser") long idUser){
        return iReponseService.addAnswer(reponse,idQuestion,idUser);
    }
    @GetMapping("/admin/getAllReponse")
    public List<Reponse> getAllReponse() {
        return iReponseService.getAllReponse();
    }

    @PutMapping("/updateReponse")
    public Reponse updateAnswer(@RequestBody Reponse reponse) {
        return iReponseService.updateAnswer(reponse);
    }
    @DeleteMapping("deleteResponse/{idResponse}")
    public String deleteResponse(long idResponse) {
        return iReponseService.deleteResponse(idResponse);
    }
    @GetMapping("/getReponseById/{idReponse}")
    public Reponse getReponseById(@PathVariable("idReponse") long idReponse) {
        return iReponseService.getById(idReponse) ;
    }
    @GetMapping("/incorrect-reponses-count/{domaineId}")
    public long countIncorrectReponsesInDomaine(@PathVariable("domaineId") Long domaineId) {
        // Chargez le domaine à partir de votre repository de domaine
        Domaine domaine = domaineRepository.findById(domaineId).orElse(null);

        if (domaine != null) {
            return questionService.countIncorrectReponsesInDomaine(domaine);
        } else {
            // Gérez le cas où le domaine n'existe pas
            return -1;
        }
    }

    @PostMapping("/admin/addDomaine")
    public Domaine addDomaine(@RequestBody Domaine domaine) {
        return iDomaineService.addDomaine(domaine);
    }
    @GetMapping("getDomaineById/{idDomaine}")
    public Domaine getDomaineById(@PathVariable("idDomaine") long idDomaine){
        return iDomaineService.getDomaineById(idDomaine);
    }
    @PutMapping("/updateDomaine")
    public Domaine updateDomaine(@RequestBody  Domaine domaine){
        return iDomaineService.updateDomaine(domaine);
    }
    @DeleteMapping("deleteDomaine/{idDomaine}")
    public String deleteDomaine(@PathVariable("idDomaine") long idDomaine) {
        return iDomaineService.deleteDomaine(idDomaine);
    }
    @GetMapping("/user/getAllDomaine")
    public List<Domaine> getAllDomaine() {
        return iDomaineService.getAllDomaine();
    }

    @PostMapping("/admin/ajouter{idQuestion}/{idUser}")
    public Reponse addAnswer(@RequestBody Reponse reponse ,@PathVariable("idQuestion") long idQuestion ,@PathVariable("idUser") long idUser,@RequestParam  List<Option> optionsChoisies)  {
        return reponseService.addAnswer(reponse,idQuestion,idUser,optionsChoisies);
    }

    }
