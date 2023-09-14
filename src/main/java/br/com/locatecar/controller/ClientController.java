package br.com.locatecar.controller;

import br.com.locatecar.exceptions.DuplicateException;
import br.com.locatecar.model.CompanyEntity;
import br.com.locatecar.model.Person;
import br.com.locatecar.model.PersonalEntity;
import br.com.locatecar.model.enums.GENDER_TYPE;
import br.com.locatecar.service.impl.PersonService;
import br.com.locatecar.utils.ListClassAttributesUtils;
import br.com.locatecar.utils.ScannerInput;

import java.util.List;

public class ClientController {
    private final PersonService personService;

    public ClientController(PersonService personService) {
        this.personService = personService;
    }

    public void createClient() {
        System.out.println("""
                Which client are you?
                1 - Personal Entity
                2 - Company Entity""");
        int clientChoice = ScannerInput.getInt();

        System.out.println("Inform your client name:");
        String name = ScannerInput.getString();
        System.out.println("Inform your password:");
        String password = ScannerInput.getString();
        System.out.println("Inform your email:");
        String email = ScannerInput.getString();
        System.out.println("Inform your address:");
        String address = ScannerInput.getString();
        System.out.println("Inform your phone number:");
        String phoneNumber = ScannerInput.getString();

        switch (clientChoice) {
            case 1 -> {
                try {
                    System.out.println("Inform your age:");
                    int age = ScannerInput.getInt();

                    String cpf = "";
                    do {
                        System.out.println("Inform you CPF");
                        cpf = ScannerInput.getString();

                        if (cpf.length() != 11) {
                            System.out.println("CPF must have 11 digits");
                        }
                    } while (cpf.length() != 11);

                    GENDER_TYPE genderType = null;
                    while (genderType == null) {
                        System.out.println("Choose your gender type: (1 - Male | 2 - Female | 3 - Other)");
                        int option = ScannerInput.getInt();

                        switch (option) {
                            case 1 -> genderType = GENDER_TYPE.MALE;
                            case 2 -> genderType = GENDER_TYPE.FEMALE;
                            case 3 -> genderType = GENDER_TYPE.OTHER;
                            default -> System.out.println("Choose a valid option.");
                        }
                    }
                    GENDER_TYPE.getAllDisplayNames();

                    PersonalEntity personalEntity = new PersonalEntity(name, cpf, email, address, password,
                            phoneNumber, age, genderType);
                    personService.add(personalEntity);
                    System.out.println("Successfully created!");
                } catch (DuplicateException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case 2 -> {
                String cnpj = "";
                do {
                    System.out.println("Inform the company CNPJ");
                    cnpj = ScannerInput.getString();

                    if (cnpj.length() != 14) {
                        System.out.println("CNPJ must have 14 digits");
                    }
                } while (cnpj.length() != 14);
                CompanyEntity companyEntity = new CompanyEntity(name, cnpj, email, address, password,
                        phoneNumber);
                personService.add(companyEntity);
                System.out.println("Successfully created!");
            }
            default -> System.out.println("Not a choice!");
        }
    }

    public void listAllCompanyEntitiesClients() {
        List<CompanyEntity> companyEntitiesClients = personService.getAll()
                .stream()
                .filter(client -> client instanceof CompanyEntity)
                .map(client -> (CompanyEntity) client)
                .toList();

        if (!companyEntitiesClients.isEmpty()) {
            System.out.println("All Company Entity Clients: ");
            for (CompanyEntity companyEntity : companyEntitiesClients) {
                System.out.println(companyEntity);
            }
        } else {
            System.out.println("There are no company entity clients register!");
        }
    }

    public void listAllPersonalEntitiesClients() {
        List<PersonalEntity> personalEntityClients = personService.getAll()
                .stream()
                .filter(client -> client instanceof PersonalEntity)
                .map(client -> (PersonalEntity) client)
                .toList();

        if (!personalEntityClients.isEmpty()) {
            System.out.println("All Personal Entity Clients: ");
            for (PersonalEntity personalEntity : personalEntityClients) {
                System.out.println(personalEntity);
            }
        } else {
            System.out.println("There are no personal entity clients register!");
        }
    }

    public void listAllUsers() {
        List<Person> clients = personService.getAll();

        if (clients.isEmpty()) {
            System.out.println("There are no clients register!");
        } else {
            System.out.println("All Clients: ");

            for (Person person : clients) {
                System.out.println(person);
            }
        }
    }

    public void updateClientInfos() {
        List<Person> users = personService.getAll();

        if (!users.isEmpty()) {
            for (Person user : users) {
                System.out.println(user);
            }
            System.out.println();
            System.out.println("What user do you want do update? (Inform by document)");
            String document = ScannerInput.getString();

            if (document.length() == 11) {
                PersonalEntity personEntity = (PersonalEntity) personService.getOneByPrimaryKey(document);

                if (personEntity != null) {
                    List<String> availableAttributes = ListClassAttributesUtils.getAvailableAttributes(personEntity.getClass());

                    System.out.println("Choose which attribute to update:");
                    for (int i = 0; i < availableAttributes.size(); i++) {
                        System.out.println((i) + " - " + availableAttributes.get(i));
                    }

                    int choice = ScannerInput.getInt();
                    if (choice >= 0 && choice < availableAttributes.size()) {
                        String selectedAttribute = availableAttributes.get(choice);
                        System.out.println("You selected to update: " + selectedAttribute);

                        System.out.println("Enter the new value for " + selectedAttribute + ":");
                        String newValue = ScannerInput.getString();

                        switch (selectedAttribute) {
                            case "name" -> personEntity.setName(newValue);
                            case "password" -> personEntity.setPassword(newValue);
                            case "email" -> personEntity.setEmail(newValue);
                            case "address" -> personEntity.setAddress(newValue);
                            case "phoneNumber" -> personEntity.setPhoneNumber(newValue);
                            case "age" -> personEntity.setAge(Integer.parseInt(newValue));
                            case "GENDER_TYPE" -> {
                                GENDER_TYPE genderType = null;
                                while (genderType == null) {
                                    System.out.println("Choose your gender type: (1 - Male | 2 - Female | 3 - Other)");
                                    int option = ScannerInput.getInt();

                                    switch (option) {
                                        case 1 -> genderType = GENDER_TYPE.MALE;
                                        case 2 -> genderType = GENDER_TYPE.FEMALE;
                                        case 3 -> genderType = GENDER_TYPE.OTHER;
                                        default -> System.out.println("Choose a valid option.");
                                    }
                                }
                                personEntity.setGenderType(genderType);
                            }
                            default -> System.out.println("Not an attribute name!");
                        }
                    } else {
                        System.out.println("Not a valid choice");
                    }
                } else {
                    System.out.println("Client not created.");
                }
            } else if (document.length() == 14) {
                CompanyEntity companyEntity = (CompanyEntity) personService.getOneByPrimaryKey(document);

                if (companyEntity != null) {
                    List<String> availableAttributes = ListClassAttributesUtils.getAvailableAttributes(companyEntity.getClass());

                    System.out.println("Choose which attribute to update:");
                    for (int i = 0; i < availableAttributes.size(); i++) {
                        System.out.println((i) + " - " + availableAttributes.get(i));
                    }

                    int choice = ScannerInput.getInt();
                    if (choice >= 0 && choice < availableAttributes.size()) {
                        String selectedAttribute = availableAttributes.get(choice);
                        System.out.println("You selected to update: " + selectedAttribute);

                        System.out.println("Enter the new value for " + selectedAttribute + ":");
                        String newValue = ScannerInput.getString();

                        switch (selectedAttribute) {
                            case "name" -> companyEntity.setName(newValue);
                            case "password" -> companyEntity.setPassword(newValue);
                            case "email" -> companyEntity.setEmail(newValue);
                            case "address" -> companyEntity.setAddress(newValue);
                            case "phoneNumber" -> companyEntity.setPhoneNumber(newValue);
                            default -> System.out.println("Not an attribute name!");
                        }
                    } else {
                        System.out.println("Not a valid choice");
                    }
                } else {
                    System.out.println("Client not created.");
                }
            } else {
                System.out.println("Invalid document");
            }
        } else {
            System.out.println("There are no Users registered!");
        }
    }

    public void removeUser() {
        listAllUsers();

        System.out.println();
        System.out.println("Which user do you want to remove? (Choose by the own document)");
        String document = ScannerInput.getString();

        if (document.length() == 11) {
            PersonalEntity person = (PersonalEntity) personService.getOneByPrimaryKey(document);

            if (person != null) {
                personService.delete(person);
                System.out.println("Client successfully deleted!");
            } else {
                System.out.println("Client not created.");
            }
        } else if (document.length() == 14) {
            CompanyEntity person = (CompanyEntity) personService.getOneByPrimaryKey(document);

            if (person != null) {
                personService.delete(person);
                System.out.println("Client successfully deleted!");
            } else {
                System.out.println("Client not created.");
            }
        } else {
            System.out.println("Document not found!");
        }
    }
}
