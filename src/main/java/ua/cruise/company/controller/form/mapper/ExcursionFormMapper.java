package ua.cruise.company.controller.form.mapper;

import ua.cruise.company.controller.form.ExcursionForm;
import ua.cruise.company.entity.Excursion;

public class ExcursionFormMapper implements EntityFormMapper<Excursion, ExcursionForm>, FormEntityMapper<Excursion, ExcursionForm> {

    @Override
    public ExcursionForm fillForm(Excursion entity) {
        ExcursionForm excursionForm = new ExcursionForm();

        excursionForm.setNameEn( entity.getNameEn());
        excursionForm.setNameUkr( entity.getNameUkr());
        excursionForm.setDescriptionEn( entity.getDescriptionEn());
        excursionForm.setDescriptionUkr( entity.getDescriptionUkr());
        excursionForm.setApproximateDurationHr( entity.getApproximateDurationHr());
        excursionForm.setPriceUSD( entity.getPriceUSD());
        excursionForm.setSeaportId( entity.getSeaport().getId());

        return excursionForm;
    }

    @Override
    public Excursion mapToEntity(ExcursionForm form) {
        return Excursion.builder()
                .nameEn(form.getNameEn())
                .nameUkr(form.getNameUkr())
                .descriptionEn(form.getDescriptionEn())
                .descriptionUkr(form.getDescriptionUkr())
                .approximateDurationHr(form.getApproximateDurationHr())
                .priceUSD(form.getPriceUSD())
                .build();
    }
}
