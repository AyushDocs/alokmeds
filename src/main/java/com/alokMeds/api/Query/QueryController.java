package com.alokMeds.api.Query;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins={"http://localhost:3000","http://localhost:8080","alokmeds.herokuapp.com"}, allowedHeaders = "*")
@RequestMapping("/api/query")
@AllArgsConstructor
public class QueryController {
    private QueryRepository queryRepository;

    @PostMapping("/")
    public void save(@RequestBody QueryRecieved[] queryParam) {
        queryRepository.save(QueryRecieved.queryRecievedToQuery(queryParam[0]));
    }

    @GetMapping("/")
    public Page<Query> findAll(@RequestParam Optional<Integer> offset,
     @RequestParam Optional<Integer> size,@RequestParam Optional<String> sortBy) {
        return queryRepository.findWithPagination(offset, size, sortBy);          
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Query> findById(@PathVariable String id) {
        return ResponseEntity.ok(queryRepository.findById(id).get());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
     queryRepository.deleteById(id);
     }

    @DeleteMapping("/")
    public void deleteAll() {
    queryRepository.deleteAll();
    }
}