package ua.cruise.company.controller.form.mapper;

@FunctionalInterface
public interface EntityFormMapper<T, F> {
    F fillForm(T entity);
}