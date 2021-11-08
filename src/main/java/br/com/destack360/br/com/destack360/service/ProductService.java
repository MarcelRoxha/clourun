package br.com.destack360.br.com.destack360.service;

import br.com.destack360.br.com.destack360.entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private static final String COLLECTIO_NAME ="products";


    public String saveProduct(Product product) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();


       ApiFuture<WriteResult> apiFutureColletion=dbFirestore.collection(COLLECTIO_NAME).document(product.getName()).set(product);
        return apiFutureColletion.get().getUpdateTime().toString();

    }


    public Product getProductsDetailsByName(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();


        DocumentReference documentReference =dbFirestore.collection(COLLECTIO_NAME).document(name);
        ApiFuture<DocumentSnapshot> future=documentReference.get();

        DocumentSnapshot documentSnapshot = future.get();
        if(documentSnapshot.exists()){
            Product product = documentSnapshot.toObject(Product.class);
            return product;
        }else {
            return null;
        }

    }

    public String updateProducts(Product product) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();


        ApiFuture<WriteResult> apiFutureColletion=dbFirestore.collection(COLLECTIO_NAME).document(product.getName()).set(product);
        return apiFutureColletion.get().getUpdateTime().toString();

    }

    public List<Product> getProductsAllDetails() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();


        Iterable<DocumentReference> documentReference =dbFirestore.collection(COLLECTIO_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

    List<Product> productList = new ArrayList<>();
    Product product = null;
      while (iterator.hasNext()){
          DocumentReference documentReference1=iterator.next();
          ApiFuture<DocumentSnapshot> future1 = documentReference1.get();
          DocumentSnapshot documentSnapshot=future1.get();
          product=documentSnapshot.toObject(Product.class);
        productList.add(product);

      }
      return productList;
    }

}
