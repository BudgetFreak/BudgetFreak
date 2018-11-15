package de.budgetfreak.budgeting.persistence;

import de.budgetfreak.budgeting.domain.CategoryType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTypeConverterTest {

    private CategoryTypeConverter testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new CategoryTypeConverter();
    }

    @Test
    public void shouldConvertEnum() {
        assertThat(testSubject.convertToDatabaseColumn(CategoryType.USER_DEFINED)).isEqualTo(CategoryType.USER_DEFINED.getKey());
    }

    @Test
    public void shouldConvertString() {
        assertThat(testSubject.convertToEntityAttribute(CategoryType.USER_DEFINED.getKey())).isEqualTo(CategoryType.USER_DEFINED);
    }

    @Test
    public void shouldConvertBackAndForth() {
        CategoryType categoryType = CategoryType.INCOME_NEXT_MONTH;
        String databaseValue = testSubject.convertToDatabaseColumn(categoryType);
        CategoryType enumValue = testSubject.convertToEntityAttribute(databaseValue);
        assertThat(enumValue).isEqualTo(categoryType);
    }
}