package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

  @Test
  public void deveRetornarTrueParaDatasFuturas() {
    LocalDate data = LocalDate.of(2030, 12, 31);
    Assert.assertTrue(DateUtils.isEqualOrFutureDate(data));
  }

  @Test
  public void deveRetornarTrueParaDataPresente() {
    LocalDate data = LocalDate.now();
    Assert.assertTrue(DateUtils.isEqualOrFutureDate(data));
  }

  @Test
  public void deveRetornarFalseParaDatasPassadas() {
    LocalDate data = LocalDate.of(2024, 12, 31);
    Assert.assertFalse(DateUtils.isEqualOrFutureDate(data));
  }
}
