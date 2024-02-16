package com.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer catId;

    @Column(name = "category_name", unique = true, nullable = false)
    private String catName;

    @Column(name = "category_description", length = 500, nullable = false)
    private String catDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> post;
}
