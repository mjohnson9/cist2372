package johnson.michael.bankaccount;

public interface InputValidator<T> {

  boolean validateInput(T input);
}
