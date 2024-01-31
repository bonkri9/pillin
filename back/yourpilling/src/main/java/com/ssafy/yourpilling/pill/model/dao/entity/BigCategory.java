package com.ssafy.yourpilling.pill.model.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bigcategory")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BigCategory {

    @Id
    @Column(name = "big_category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bigCategoryId;

    @Column(name = "category_nm")
    private String categoryNm;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "bigCategory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<MidCategory> midCategorys;
}
