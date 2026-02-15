package es.damdi.andresrl.adressappmavenjavafx.model;

import flexjson.JSON;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 *
 * @author Marco Jakob
 */
public class PersonListWrapper {

    @JSON(include = true)
    private List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


    public String toJson() {
        List<Map<String, Object>> personMaps = new ArrayList<>();
        for (Person p : persons) {
            personMaps.add(p.toMap());
        }
        Map<String, Object> wrapperMap = new HashMap<>();
        wrapperMap.put("persons", personMaps);

        return new JSONSerializer()
                .exclude("*.class")
                .deepSerialize(wrapperMap);
    }



    public static PersonListWrapper fromJson(String json) {
        try {
            Map<String, Object> wrapperMap = new JSONDeserializer<Map<String, Object>>().deserialize(json);
            PersonListWrapper wrapper = new PersonListWrapper();
            List<Map<String, Object>> personMaps = (List<Map<String, Object>>) wrapperMap.get("persons");

            if (personMaps == null) {
                return wrapper;
            }

            for (Map<String, Object> personMap : personMaps) {
                Person person = new Person();

                // Asignar valores con manejo de nulos y conversiones
                person.setFirstName((String) personMap.getOrDefault("firstName", ""));
                person.setLastName((String) personMap.getOrDefault("lastName", ""));
                person.setStreet((String) personMap.getOrDefault("street", ""));

                // Convertir números a Integer de forma segura
                Object postalCode = personMap.get("postalCode");
                if (postalCode != null) {
                    person.setPostalCode(((Number) postalCode).intValue());
                }

                person.setCity((String) personMap.getOrDefault("city", ""));

                Object birthday = personMap.get("birthday");
                if (birthday != null) {
                    person.setBirthday(((Number) birthday).intValue());
                }

                wrapper.getPersons().add(person);
            }

            return wrapper;
        } catch (Exception e) {
            e.printStackTrace();
            return new PersonListWrapper();
        }
    }

}
