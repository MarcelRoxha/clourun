package br.com.destack360.br.com.destack360.controller;


import br.com.destack360.br.com.destack360.entity.Product;
import br.com.destack360.br.com.destack360.service.ProductService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {


    @Autowired
    private ProductService productService;
    private Firestore firestore;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity saveProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {

        try {
            WriteResult writeResult = this.firestore.collection("users").document(product.getId() != null ? product.getId() : UUID.randomUUID().toString()).set(product).get();
            return new ResponseEntity(writeResult, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

/*

    @GetMapping("/products/{name}")
    public Product getProductsDetailsByName(@PathVariable String name) throws ExecutionException, InterruptedException {



        return productService.getProductsDetailsByName(name);
    }

    @PutMapping("/products")
    public String updateProducts(@RequestBody Product product) throws ExecutionException, InterruptedException {




        return productService.updateProducts(product);
    }
*/


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable String id) {

        try {
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = this.firestore.collection("users").document(id).get();
            Book payload = documentSnapshotApiFuture.get().toObject(Book.class);
            return new ResponseEntity(payload, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {

        try {
            List<Map<String, Object>> payload = new ArrayList<>();

            ApiFuture<QuerySnapshot> results = this.firestore.collection("users").get();
            results.get().getDocuments().stream().forEach(action -> {
                payload.add(action.getData());
            });

            return new ResponseEntity(payload, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

/*


    @GetMapping("/products")
    public List<Product> getProductsAllDetails() throws ExecutionException, InterruptedException {




        return productService.getProductsAllDetails();
    }
*/

}
