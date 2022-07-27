package com.sprint.sprint5.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String nome;
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private LocalDateTime dataCriacao;
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private LocalDateTime dataValidade;
     private double valor;
     private String descricao;

     @ManyToMany(cascade = CascadeType.MERGE)
     @JoinColumn(name = "oferta_id")
     @Nullable
     private List<Oferta> ofertas;


}
