package br.com.locatecar.model;

import br.com.locatecar.model.enums.GENDER_TYPE;

public class PersonalEntity extends Person {

    private Integer age;
    private GENDER_TYPE genderType;

    public PersonalEntity(String name, String cpf, String email, String address, String password, String phoneNumber, Integer age, GENDER_TYPE genderType) {
        super(name, cpf, email, address, password, phoneNumber);
        this.age = age;
        this.genderType = genderType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public GENDER_TYPE getGenderType() {
        return genderType;
    }

    public void setGenderType(GENDER_TYPE genderType) {
        this.genderType = genderType;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", age=" + age + '\'' +
                ", genderType=" + genderType +
                '}';
    }
}
