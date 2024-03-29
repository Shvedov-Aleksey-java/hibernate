package hibernate.starter.criteriaAPI;

import com.querydsl.core.types.ExpressionUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import com.querydsl.core.types.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


/**
 * Данный класс это составление состовленние динамического запроса с помощью queryDsl
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QPredicate {

    private final List<Predicate> predicates = new ArrayList<>();

    /**
     * обьявили приватный класс и возврощаем его что бы потом наполнить предикатами
     * @return
     */
    public static QPredicate builder() {
    return new QPredicate();
    }

    /**
     * добовление придикаты с дальнейшим возвращением текущего обьекта
     * @param object обьект с которым будут сравнивать
     * @param function интерфейс у которого один метод по факту возврощает Predicate
     * @return возврощает текущий обьект с уже добавленной предикатой
     * @param <T> любой обьект для сравнения
     */
    public <T> QPredicate add (T object, Function<T, Predicate> function) {
        Optional.ofNullable(object).ifPresent(o1 -> predicates.add(function.apply(o1)));
        return this;
    }

    /**
     * ExpressionUtils.allOf принимает на вход лист предикат
     * @return возврощает уже обьект Query.Predicate через "И"
     */
    public Predicate buildAnd() {
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * ExpressionUtils.anyOf принимает на вход лист предикат
     * @return возврощает уже обьект Query.Predicate через "ИЛИ"
     */
    public Predicate buildOr() {
        return ExpressionUtils.anyOf(predicates);
    }
}