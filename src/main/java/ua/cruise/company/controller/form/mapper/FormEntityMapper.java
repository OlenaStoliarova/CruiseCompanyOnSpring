package ua.cruise.company.controller.form.mapper;

@FunctionalInterface
public interface FormEntityMapper<T, F> {
    T mapToEntity(F form);
}
