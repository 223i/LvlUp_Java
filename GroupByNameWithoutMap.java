import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class GroupByNameWithoutMap {

    public static void main(String[] args) {
        String[] namesDictionary = new String[] { "Jack", "John", "Samuel", "Tomas", "Daniel", "Harry","Michael"};
        String[] letters = new String[] { "L.", "C.", "D.", "E.", "S.", "P" };

        ArrayList<Person> allPerson = createData(namesDictionary, letters);
        ArrayList<GroupedElement> groups = new ArrayList<>();

        HashSet<String> allUniqueNames = new HashSet<>();

        for (Person person : allPerson) {
            allUniqueNames.add(person.firstName);
        }

        for (String uniqueName : allUniqueNames){
            ArrayList<Person> newList = new ArrayList<>();
            GroupedElement newGroup = new GroupedElement(uniqueName, newList );
            groups.add(newGroup);
        }

        for (Person person : allPerson){
            for (GroupedElement groupedElement : groups){
                if ((!groupedElement.personWithOneName.contains(person))&&
                        groupedElement.groupingParameter.equals(person.firstName) ) {
                    groupedElement.addNewPersonWithThisName(person, groupedElement.personWithOneName);
                }
            }
        }

        for (GroupedElement group : groups){
            System.out.println("Group with name -" + group.groupingParameter);
            for (Person person : group.personWithOneName){
                System.out.println(person.firstName + " " + person.lastName);
            }
        }

    }

    public static ArrayList<Person> createData(String [] namesDictionary, String [] firstLettersSurname){
        Random random = new Random();
        ArrayList<Person> allPersons = new ArrayList<>(50);
        for (int i = 0; i < 100; i++) {
            String firstName = namesDictionary[random.nextInt(namesDictionary.length)];
            String lastName = firstLettersSurname[random.nextInt(firstLettersSurname.length)];
            Person newPerson = new Person(firstName, lastName);
            allPersons.add(newPerson);
        }
        return allPersons;
    }



    static class GroupedElement{
        private String groupingParameter;
        private ArrayList<Person> personWithOneName = new ArrayList<>();

        GroupedElement(String newName, ArrayList<Person> personsWithNewName){
            groupingParameter = newName;
            personWithOneName = personsWithNewName;
        }

        public void addNewPersonWithThisName(Person somePerson,ArrayList<Person> personsWithOneName){
            if (!personsWithOneName.contains(somePerson)){
                personsWithOneName.add(somePerson);
            }
        }
    }

    static class Person {
        private String firstName;
        private String lastName;

        Person(String name, String surname){
            firstName = name;
            lastName = surname;
        }
    }
}