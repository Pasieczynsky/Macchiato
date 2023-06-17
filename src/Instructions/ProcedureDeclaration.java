package Instructions;

import Exceptions.MacchiatoException;

// Macchiato w wersji 1.1 ma procedury. O procedurach myśleć można jak o funkcjach,
// które mają zero lub więcej parametrów oraz nie dają żadnej wartości (innymi słowy są typu void).
// Można je zadeklarować, a następnie w ciągu instrukcji wywołać z odpowiednimi argumentami.

//        Deklaracja procedury zawiera:
//        nagłówek, czyli informacje o nazwie procedury i jej parametrach (wszystkie są typu całkowitego).
//        Nazwa procedury jest niepustym ciągiem liter alfabetu angielskiego od 'a' do 'z',
//        natomiast nazwy parametrów podlegają takim ograniczeniom jak nazwy zmiennych, to znaczy są jednoliterowe.
//        Wszystkie nazwy parametrów muszą być różne. Parametry są przekazywane przez wartość.
//        Deklaracje procedur działają w sposób podobny do deklaracji zmiennych, to jest:
//        znajdują się w bloku w tym samym miejscu, co deklaracje zmiennych, to znaczy na początku bloku w ciągu deklaracji zmiennych i procedur,
//        są widoczne do końca swojego bloku,
//        mogą być przesłaniane,
//        w tym samym bloku nie można zadeklarować dwa razy procedury o tej samej nazwie,

//        treść procedury, która składa się z ciągu deklaracji, po których następuje ciąg instrukcji wykonywanych w momencie wywołania procedury.

//        Wywołanie procedury jest instrukcją, która zawiera:
//        nazwę procedury,
//        argumenty, będące wyrażeniami języka Macchiato.
//        Argumenty wyliczane są w momencie wywołania procedury (w kolejności w jakiej zostały zapisane,
//        tj. od pierwszego do ostatniego) i w jej treści dostępne są jako wartości zmiennych odpowiadających parametrom procedury.
//        Wywołanie procedury skutkuje wykonaniem po kolei instrukcji z jej treści, przy czym dzieje się to w sposób zgodny z dynamicznym wiązaniem zmiennych.
//        To znaczy, że jeśli w treści procedury występuje odwołanie do zmiennej,
//        to ustalanie, o którą zmienną chodzi (jeśli jest w programie więcej niż jedna o tej samej nazwie i czy w ogóle taka zmienna jest dostępna)
//        dzieje się to w trakcie wykonania procedury. Wykorzystywana jest zmienna widoczna obecnie w środowisku wykonania procedury.
//        Uwaga: dynamiczne wiązanie zmiennych jest łatwiejsze do zaimplementowania (i dlatego wybrano je w Macchiato),
//        ale praktycznie nie jest stosowane. W językach takich jak C, Java czy Python występuje statyczne wiązanie zmiennych.

// moze trzba bedzie rozbic na 2 klasy
// jedna bedzie przechowywac nazwe, parametry, blok
// druga bedzie odpowiadać za wywołanie procedury

public class ProcedureDeclaration implements Instruction{
    private String name;
    private char [] parameters;
    private ProcedureBlock definition;

    public ProcedureDeclaration(String name, char [] parameters, ProcedureBlock blockInstruction){
        this.name = name;
        this.parameters = parameters;
        this.definition = blockInstruction;
    }

    @Override
    public void execute(Block parent) throws MacchiatoException {
        parent.addProcedure(this);
    }
    public String getName(){
        return name;
    }
    @Override
    public Boolean nextInstructionExecute(Block parent) throws MacchiatoException {
        execute(parent);
        return true;
    }

    @Override
    public void printNextInstruction(Block parent) {
        String params = getParametersString();
        System.out.println("Proc " + name + " (" + params  + ")" );
    }
    private String getParametersString(){
        //Zamień parameters na String oddzielając przecinkami
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            params.append(parameters[i]);
            if(i != parameters.length - 1){
                params.append(", ");
            }
        }
        return params.toString();
    }
    @Override
    public void display(Block parent, int depth) {
        parent.printVariables(depth);
    }

    public String getProcedureName() {
        return name;
    }
    public ProcedureBlock getProcedureDefinition(){
        return definition;
    }
    public char [] getParameters(){
        return parameters;
    }
}
