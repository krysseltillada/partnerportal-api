package com.codebytes.partnerportal.api.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryPrinter
{
    private StringBuilder spaceBuilder = new StringBuilder(StringUtils.EMPTY);
    private int spaceOffsetPerCategory;
    private int spaceCounter = 0;


    private List<Category> mCategoryList;

    private List<Category> getCategoryPath(Category pCategory) {

        mCategoryList.add(pCategory);

        if(pCategory.getParentCategory() != null) {
            getCategoryPath(pCategory.getParentCategory());
        }

        return mCategoryList;
    }

    public String createRootCategoryPath(Category pCategory) {

        if (mCategoryList == null) {
            mCategoryList = new ArrayList<>();
        } else {
            mCategoryList.clear();
        }

        List<Category> parentCategories = getCategoryPath(pCategory);
        StringBuilder categoryPathBuilder = new StringBuilder(StringUtils.EMPTY);

        for(int i = parentCategories.size() - 1;
                i > 0; --i) {

            categoryPathBuilder.append(parentCategories.get(i).getCategoryName());
            categoryPathBuilder.append(">");
        }

        categoryPathBuilder.append(parentCategories.get(0).getCategoryName());

        return categoryPathBuilder.toString();
    }

    public void print(Category pCategory)
    {

        System.out.print(spaceBuilder.toString());
        System.out.println("*" + pCategory.getCategoryName());

        boolean isThereSubCategory = !pCategory.getSubCategory().isEmpty();

        if (isThereSubCategory)
        {
            addSpaces();
            iterateCategories(pCategory);
            deleteSpaces();

        } else {
            addSpaces();
            deleteSpaces();
        }

    }

    private void iterateCategories(Category pCategory) {
        for (Category category : pCategory.getSubCategory())
        {
            print(category);
        }
    }

    private void addSpaces () {
        spaceCounter += spaceOffsetPerCategory;
        for (int i = 0; i < spaceOffsetPerCategory; ++i)
        {
            spaceBuilder.append(" ");
        }
    }

    private void deleteSpaces () {
        spaceBuilder.delete(spaceCounter - spaceOffsetPerCategory, spaceCounter);
        spaceCounter -= spaceOffsetPerCategory;
    }

    public void setSpaceOffsetPerCategory(int pSpaceOffsetPerCategory) {
        spaceOffsetPerCategory = pSpaceOffsetPerCategory;
    }
}
