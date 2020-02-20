package ua.cruise.company.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ExcursionForm {
    @Pattern(regexp = "^[a-zA-Z]+([ ]?['-:,&.]?[ ]?[a-zA-Z]+)*$",
            message="Validation failed for name in English. Only latin letters and delimiter characters are allowed")
    private String nameEn;

    @Pattern(regexp = "^[А-ЩЬЮЯҐІЇЄа-щьюяґіїє]+([ ]?['-:,&.]?[ ]?[А-ЩЬЮЯҐІЇЄа-щьюяґіїє]+)*$",
            message="Validation failed for name in Ukrainian. Only Ukrainian letters and delimiter characters are allowed")
    private String nameUkr;

    @Size(min = 10, message = "The description in English is too short. At least 10 characters are expected")
    private String descriptionEn;
    @Size(min = 10, message = "The description in Ukrainian is too short. At least 10 characters are expected")
    private String descriptionUkr;

    @Min(1)
    @Max(72)
    private Long approximateDurationHr;

    @Min(0)
    @Max(999999)
    private BigDecimal priceUSD;

    private Long seaportId;

    public ExcursionForm() {
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionUkr() {
        return descriptionUkr;
    }

    public void setDescriptionUkr(String descriptionUkr) {
        this.descriptionUkr = descriptionUkr;
    }

    public Long getApproximateDurationHr() {
        return approximateDurationHr;
    }

    public void setApproximateDurationHr(Long approximateDurationHr) {
        this.approximateDurationHr = approximateDurationHr;
    }

    public BigDecimal getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(BigDecimal priceUSD) {
        this.priceUSD = priceUSD;
    }

    public Long getSeaportId() {
        return seaportId;
    }

    public void setSeaportId(Long seaportId) {
        this.seaportId = seaportId;
    }

    @Override
    public String toString() {
        return "ExcursionForm{" +
                "nameEn='" + nameEn + '\'' +
                ", nameUkr='" + nameUkr + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", descriptionUkr='" + descriptionUkr + '\'' +
                ", approximateDurationHr=" + approximateDurationHr +
                ", priceUSD=" + priceUSD +
                ", seaportId=" + seaportId +
                '}';
    }
}
