package com.example.myYouTube.service;


import com.example.myYouTube.dto.category.CategoryDto;
import com.example.myYouTube.entity.CategoryEntity;
import com.example.myYouTube.exp.AppBadException;
import com.example.myYouTube.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDto create(CategoryDto category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        Optional<CategoryEntity> exists = categoryRepository.findByName(category.getName());
        if (exists.isPresent()) {
            throw new AppBadException("Category name already exists");
        }
        categoryEntity.setName(category.getName());
        categoryRepository.save(categoryEntity);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryEntity.getId());
        categoryDto.setCreatedDate(categoryEntity.getCreatedDate());
        categoryDto.setName(categoryEntity.getName());
        return categoryDto;
    }



    public CategoryEntity findById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new AppBadException("Category not found"));
    }

    public Boolean update(Integer id, CategoryDto dto) {
        CategoryEntity categoryEntity = findById(id);
        categoryEntity.setName(dto.getName());
        categoryRepository.save(categoryEntity);
        return true;
    }

    public Boolean delete(Integer id) {
        CategoryEntity categoryEntity = findById(id);
        categoryRepository.delete(categoryEntity);
        return true;
    }

    public List<CategoryDto> getList() {
        Iterable<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDto> list = new LinkedList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryEntity.getId());
            categoryDto.setName(categoryEntity.getName());
            categoryDto.setCreatedDate(categoryEntity.getCreatedDate());
            list.add(categoryDto);
        }
        return list;
    }
}
