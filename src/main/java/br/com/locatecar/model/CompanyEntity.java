package br.com.locatecar.model;

public class CompanyEntity extends Person {

    public CompanyEntity(String name, String cnpj, String email, String address, String password, String phoneNumber) {
        super(name, cnpj, email, address, password, phoneNumber);
    }

    @Override
    public String toString() {
        return super.toString() + "}";
    }
}
