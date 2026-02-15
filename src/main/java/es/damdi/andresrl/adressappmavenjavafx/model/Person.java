    package es.damdi.andresrl.adressappmavenjavafx.model;

    import javafx.beans.property.IntegerProperty;
    import javafx.beans.property.SimpleIntegerProperty;
    import javafx.beans.property.SimpleStringProperty;
    import javafx.beans.property.StringProperty;

    import java.util.HashMap;
    import java.util.Map;


    public class Person {

        private final StringProperty firstName;
        private final StringProperty lastName;
        private final StringProperty street;
        private final IntegerProperty postalCode;
        private final StringProperty city;
        private final IntegerProperty birthday;


        public Person() {
            this(null, null);
        }

        public Person(String firstName, String lastName) {
            this.firstName = new SimpleStringProperty(firstName != null ? firstName : "");
            this.lastName = new SimpleStringProperty(lastName != null ? lastName : "");
            this.street = new SimpleStringProperty("some street");
            this.postalCode = new SimpleIntegerProperty(1234);
            this.city = new SimpleStringProperty("some city");
            this.birthday = new SimpleIntegerProperty(2000);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String firstName) {
            this.firstName.set(firstName);
        }

        public StringProperty firstNameProperty() {
            return firstName;
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String lastName) {
            this.lastName.set(lastName);
        }

        public StringProperty lastNameProperty() {
            return lastName;
        }

        public String getStreet() {
            return street.get();
        }

        public void setStreet(String street) {
            this.street.set(street);
        }

        public StringProperty streetProperty() {
            return street;
        }

        public int getPostalCode() {
            return postalCode.get();
        }

        public void setPostalCode(int postalCode) {
            this.postalCode.set(postalCode);
        }

        public IntegerProperty postalCodeProperty() {
            return postalCode;
        }

        public String getCity() {
            return city.get();
        }

        public void setCity(String city) {
            this.city.set(city);
        }

        public StringProperty cityProperty() {
            return city;
        }

        public int getBirthday() {
            return birthday.get();
        }

        public void setBirthday(int birthday) {
            this.birthday.set(birthday);
        }

        public IntegerProperty birthdayProperty() {
            return birthday;
        }

        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>();
            map.put("firstName", getFirstName());
            map.put("lastName", getLastName());
            map.put("street", getStreet());
            map.put("postalCode", getPostalCode());
            map.put("city", getCity());
            map.put("birthday", getBirthday());
            return map;
        }

    }