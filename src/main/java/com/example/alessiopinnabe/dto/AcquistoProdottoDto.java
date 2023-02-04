package com.example.alessiopinnabe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcquistoProdottoDto extends AcquistoDto {
    private ProdottoDto prodotto;

}
