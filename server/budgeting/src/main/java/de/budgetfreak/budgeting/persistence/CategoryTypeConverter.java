package de.budgetfreak.budgeting.persistence;

import de.budgetfreak.budgeting.domain.CategoryType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Optional;

/**
 * {@link AttributeConverter} for the {@link CategoryType} enum.
 */
@Converter(autoApply = true)
public class CategoryTypeConverter implements AttributeConverter<CategoryType, String> {

    @Override
    public String convertToDatabaseColumn(CategoryType attribute) {
        return attribute.getKey();
    }

    @Override
    public CategoryType convertToEntityAttribute(String dbData) {
        Optional<CategoryType> result = Arrays.stream(CategoryType.values()).filter(categoryType -> categoryType.getKey().equals(dbData)).findFirst();
        return result.orElseThrow(() -> new IllegalStateException(String.format("Value '%s' for CategoryType unknown.", dbData)));
    }
}
