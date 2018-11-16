package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Log
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    @ManyToOne
    private Category parentCategory;

    @OneToMany(mappedBy="parentCategory", cascade=CascadeType.ALL)
    private List<Category> subCategory = new ArrayList<>();

    private String categoryName;

    public Category(String pCategoryName)
    {
        categoryName = pCategoryName;
    }

    public void addSubCategory(Category pCategory) {
        subCategory.add(pCategory);
    }

    public void printAllSubCategory(int numberOfSpaces)
    {
        CategoryPrinter categoryPrinter = new CategoryPrinter();
        categoryPrinter.setSpaceOffsetPerCategory(numberOfSpaces);
        categoryPrinter.print(this);
    }

    public void setParentCategory(Category pParentCategory) {
        parentCategory = pParentCategory;
    }

}
