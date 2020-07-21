package analysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.ArrayList;

public class Semantic implements Constants
{
    String code = "";
    Stack<Type> stack = new Stack<Type>();
    String operator = "";
    Token atrOperator;
    Type varType;
    Token varValue;
    ArrayList<String> idList = new ArrayList<>();
    ArrayList<Integer> labelList = new ArrayList<>();
    int labelStart = 0;
    HashMap<String, Type> ST = new HashMap<>();

    public String getCode(){
        return code;
    }

    public void clear(){
        code = "";
        stack.clear();
        operator = "";
        idList.clear();
        labelList.clear();
        labelStart = 0;
        ST.clear();
    }

    public void executeAction(int action, Token token)	throws SemanticError
    {
        System.out.println("Ação #"+action+", Token: "+token);
        switch (action) {
            case 1:
                action01(token);
                break;
            case 2:
                action02(token);
                break;
            case 3:
                action03(token);
                break;
            case 4:
                action04(token);
                break;
            case 5:
                action05(token);
                break;
            case 6:
                action06(token);
                break;
            case 7:
                action07(token);
                break;
            case 8:
                action08(token);
                break;
            case 9:
                action09(token);
                break;
            case 10:
                action10(token);
                break;
            case 11:
                action11();
                break;
            case 12:
                action12();
                break;
            case 13:
                action13(token);
                break;
            case 14:
                action14();
                break;
            case 15:
                action15();
                break;
            case 16:
                action16();
                break;
            case 17:
                action17(token);
                break;
            case 18:
                action18(token);
                break;
            case 19:
                action19(token);
                break;
            case 20:
                action20(token);
                break;
            case 21:
                action21(token);
                break;
            case 22:
                action22(token);
                break;
            case 23:
                action23(token);
                break;
            case 24:
                action24(token);
                break;
            case 30:
                action30(token);
                break;
            case 31:
                action31(token);
                break;
            case 32:
                action32(token);
                break;
            case 33:
                action33(token);
                break;
            case 34:
                action34(token);
                break;
            case 35:
                action35(token);
                break;
            case 36:
                action36(token);
                break;
            case 37:
                action37(token);
                break;
            case 38:
                action38(token);
                break;
            case 39:
                action39();
                break;
            case 40:
                action40();
                break;
            case 41:
                action41();
                break;
            case 42:
                action42();
                break;
            case 43:
                action43();
                break;
            case 44:
                action44();
                break;
            case 45:
                action45();
                break;
            default:
                throw new SemanticError("Ação não implementada");
        }
    }

    private void action01(Token token) throws SemanticError {
        Type type1 = stack.pop();
        Type type2 = stack.pop();

        if (type1 == type2) {
            stack.push(type1);
        }
        else if (((type1 == Type.float64) && (type2 == Type.int64)) || ((type2 == Type.float64) && (type1 == Type.int64))) {
            stack.push(Type.float64);
        }
        else {
            throw new SemanticError(token.getLexeme(), "tipo(s) incompatível(is) em expressão aritmética", token.getPosition());
        }

        code += "\t\tadd\n";
    }

    private void action02(Token token) throws SemanticError {
        Type type1 = stack.pop();
        Type type2 = stack.pop();

        if (type1 == type2) {
            stack.push(type1);
        }
        else if (((type1 == Type.float64) && (type2 == Type.int64)) || ((type2 == Type.float64) && (type1 == Type.int64))) {
            stack.push(Type.float64);
        }
        else {
            throw new SemanticError(token.getLexeme(), "tipo(s) incompatível(is) em expressão aritmética", token.getPosition());
        }

        code += "\t\tsub\n";
    }

    private void action03(Token token) throws SemanticError {
        Type type1 = stack.pop();
        Type type2 = stack.pop();

        if (type1 == type2) {
            stack.push(type1);
        }
        else if (((type1 == Type.float64) && (type2 == Type.int64)) || ((type2 == Type.float64) && (type1 == Type.int64))) {
            stack.push(Type.float64);
        }
        else {
            throw new SemanticError(token.getLexeme(), "tipo(s) incompatível(is) em expressão aritmética", token.getPosition());
        }

        code += "\t\tmul\n";
    }

    private void action04(Token token) throws SemanticError {
        Type type1 = stack.pop();
        Type type2 = stack.pop();

        if (type1 == type2) {
            if (type1 == Type.int64)
                stack.push(Type.float64);
            else
                stack.push(type1);
        }
        else if (((type1 == Type.float64) && (type2 == Type.int64)) || ((type2 == Type.float64) && (type1 == Type.int64))) {
            stack.push(Type.float64);
        }
        else {
            throw new SemanticError(token.getLexeme(), "tipo(s) incompatível(is) em expressão aritmética", token.getPosition());
        }

        code += "\t\tdiv\n";
    }

    private void action05(Token token) {
        stack.push(Type.int64);
        code += "\t\tldc.r8 " + token.getLexeme() + "\n";
    }

    private void action06(Token token) {
        stack.push(Type.float64);
        code += "\t\tldc.r8 " + token.getLexeme() + "\n";
    }

    private void action07(Token token) throws SemanticError {
        Type type = stack.pop();
        if ((type == Type.int64) || (type == Type.float64)) {
            stack.push(type);
        } else {
            throw new SemanticError(token.getLexeme(), "tipo(s) incompatível(is) em expressão aritmética", token.getPosition());
        }
    }

    private void action08(Token token) throws SemanticError {
        Type type = stack.pop();
        if ((type == Type.int64) || (type == Type.float64)) {
            stack.push(type);
        } else {
            throw new SemanticError(token.getLexeme(), "tipo(s) incompatível(is) em expressão aritmética", token.getPosition());
        }

        code += "\t\tldc.i8 -1\n" +
                "\t\tconv.r8\n" +
                "\t\tmul\n";
    }

    private void action09(Token token) {
        operator = token.getLexeme();
    }

    private void action10(Token token) throws SemanticError {
        Type type1 = stack.pop();
        Type type2 = stack.pop();

        if ((type1 == Type.string && type2 == Type.string) ||
            (type1 == Type.int64 && type2 == Type.int64) ||
            (type1 == Type.float64 && type2 == Type.float64) ||
            (type1 == Type.int64 && type2 == Type.float64) ||
            (type1 == Type.float64 && type2 == Type.int64)) {
            stack.push(Type.bool);
        } else {
            throw new SemanticError(token.getLexeme(), "tipos incompatíveis em expressão relacional", token.getPosition());
        }

        switch (operator) {
            case "<":
                code += "\t\tclt\n";
                break;
            case ">":
                code += "\t\tcgt\n";
                break;
            case "==":
                code += "\t\tceq\n";
                break;
            case "!=":
                code += "\t\tceq\n" +
                        "\t\tldc.i4.1\n" +
                        "\t\txor\n";
                break;
        }
    }

    private void action11() {
        stack.push(Type.bool);
        code += "\t\tldc.i4.1\n";
    }

    private void action12() {
        stack.push(Type.bool);
        code += "\t\tldc.i4.0\n";
    }

    private void action13(Token token) throws SemanticError {
        Type type = stack.pop();
        if (type == Type.bool) {
            stack.push(type);
        } else {
            throw new SemanticError(token.getLexeme(), "tipo(s) incompatível(is) em expressão lógica", token.getPosition());
        }

        code += "\t\tldc.i4.1\n" +
                "\t\txor\n";
    }

    private void action14() {
        Type type = stack.pop();
        if (type == Type.int64) {
            type = Type.float64;
        } else if (type == Type.bin) {
            code += "\t\tldstr \"#b\"\n" +
                    "\t\tcall void [mscorlib]System.Console::Write(string)\n" +
                    "\t\tldc.i4 2\n" +
                    "\t\tcall string [mscorlib]System.Convert::ToString(int64, int32)\n";
            type = Type.string;
        } else if (type == Type.hexa) {
            code += "\t\tldstr \"#x\"\n" +
                    "\t\tcall void [mscorlib]System.Console::Write(string)\n" +
                    "\t\tldc.i4 16\n" +
                    "\t\tcall string [mscorlib]System.Convert::ToString(int64, int32)\n";
            type = Type.string;
        }
        code += "\t\tcall void [mscorlib]System.Console::Write(" + type.toString() + ")\n";
    }

    private void action15() {
        code += ".assembly extern mscorlib {}\n" +
                ".assembly _codigo_objeto{}\n" +
                ".module   _codigo_objeto.exe\n" +
                "\n" +
                ".class public _UNICA { \n" +
                "\t.method static public void _principal() {\n" +
                "\t\t.entrypoint\n";
    }

    private void action16() {
        code += "\t\tret\n" +
                "\t}\n" +
                "}";
    }

    private void action17(Token token) throws SemanticError {
        Type type1 = stack.pop();
        Type type2 = stack.pop();

        if ((type1 == type2) && (type1 == Type.bool)) {
            stack.push(type1);
        } else {
            throw new SemanticError(token.getLexeme(), "tipo(s) incompatível(is) em expressão lógica", token.getPosition());
        }

        code += "\t\tand\n";
    }

    private void action18(Token token) throws SemanticError {
        Type type1 = stack.pop();
        Type type2 = stack.pop();

        if ((type1 == type2) && (type1 == Type.bool)) {
            stack.push(type1);
        } else {
            throw new SemanticError(token.getLexeme(), "tipo(s) incompatível(is) em expressão lógica", token.getPosition());
        }

        code += "\t\tor\n";
    }

    private void action19(Token token) {
        stack.push(Type.string);
        code += "\t\tldstr " + token.getLexeme() + "\n";
    }

    private void action20(Token token) {
        stack.push(Type.bin);
        String bin = token.getLexeme().substring(2);
        code += "\t\tldstr \"" + bin + "\"\n" +
                "\t\tldc.i4 2\n" +
                "\t\tcall int64 [mscorlib]System.Convert::ToInt64(string, int32)\n";
    }

    private void action21(Token token) {
        stack.push(Type.hexa);
        String hexa = token.getLexeme().substring(2);
        code += "\t\tldstr \"" + hexa + "\"\n" +
                "\t\tldc.i4 16\n" +
                "\t\tcall int64 [mscorlib]System.Convert::ToInt64(string, int32)\n";
    }

    private void action22(Token token) throws SemanticError {
        Type type = stack.pop();
        if (type == Type.bin || type == Type.hexa) {
            stack.push(Type.int64);
        } else {
            throw new SemanticError(token.getLexeme(), "tipo incompatível em operação de conversão de valor", token.getPosition());
        }
        code += "\t\tconv.r8\n";
    }

    private void action23(Token token) throws SemanticError {
        Type type = stack.pop();
        if (type == Type.int64 || type == Type.hexa) {
            stack.push(Type.bin);
        } else {
            throw new SemanticError(token.getLexeme(), "tipo incompatível em operação de conversão de valor", token.getPosition());
        }
        code += "\t\tconv.i8\n";
    }

    private void action24(Token token) throws SemanticError {
        Type type = stack.pop();
        if (type == Type.int64 || type == Type.bin) {
            stack.push(Type.hexa);
        } else {
            throw new SemanticError(token.getLexeme(), "tipo incompatível em operação de conversão de valor", token.getPosition());
        }
        code += "\t\tconv.i8\n";
    }

    private void action30(Token token) {
        switch (token.getLexeme()) {
            case "int":
                varType = Type.int64;
                break;
            case "float":
                varType = Type.float64;
                break;
            case "bool":
                varType = Type.bool;
                break;
            case "hexa":
                varType = Type.hexa;
                break;
            case "bin":
                varType = Type.bin;
                break;
            case "str":
                varType = Type.string;
                break;
        }
    }

    private void action31(Token token) throws SemanticError {
        for (String id:
             idList) {
            if (ST.containsKey(id)){
                throw new SemanticError(id, "já declarado", token.getPosition());
            }
            ST.put(id, varType);
            Type type = varType;
            if (type == Type.bin || type == Type.hexa) type = Type.int64;
            else if (type == Type.int64) type = Type.float64;

            code += "\t\t.locals(" + type.toString() + " " + id + ")\n";
        }
        idList.clear();
    }

    private void action32(Token token) {
        idList.add(token.getLexeme());
    }

    private void action33(Token token) throws SemanticError {
        String id = token.getLexeme();
        if (!ST.containsKey(id)) {
            throw new SemanticError(id, "não declarado", token.getPosition());
        }
        Type idType = ST.get(id);
        stack.push(idType);
        code += "\t\tldloc " + id + "\n";
    }

    private void action34(Token token) throws SemanticError {
        Iterator<String> iter
                = idList.iterator();
        while (iter.hasNext()){
            String id = idList.remove(0);
            System.out.println(id);
            if (!ST.containsKey(id)) {
                throw new SemanticError(id, "não declarado", token.getPosition());
            }

            Type idType = ST.get(id);
            System.out.println(idType);

            if (!idList.isEmpty()) {
                for (int i = 0; i <= idList.size() - 1; ++i)
                    code += "\t\tdup\n";
            }

            if (atrOperator.getId() == 47 || atrOperator.getId() == 48) {
                code += "\t\tldloc " + id + "\n";
                if (ST.get(id) == Type.int64) {
                    code += "\t\tconv.r8\n";
                }
                if (atrOperator.getId() == 47)
                    code += "\t\tadd\n";
                else {
                    code += "\t\tsub\n" +
                            "\t\tldc.i8 -1\n" +
                            "\t\tconv.r8\n" +
                            "\t\tmul\n";
                }
            }
            code += "\t\tstloc " + id + "\n";
        }
    }

    private void action35(Token token) throws SemanticError {
        for (String id:
                idList) {
            if (!ST.containsKey(id)){
                throw new SemanticError(id, "não declarado", token.getPosition());
            }
            Type idType = ST.get(id);

            code += "\t\tcall string [mscorlib]System.Console::ReadLine()\n";

            /*if (idType == Type.int64) {
                code += "\t\tcall int64 [mscorlib]System.Int64::Parse(string)\n";
            } else */if (idType == Type.float64 || idType == Type.int64) {
                code += "\t\tcall float64 [mscorlib]System.Double::Parse(string)\n";
            } else if (idType == Type.bool) {
                code += "\t\tcall bool [mscorlib]System.Boolean::Parse(string)\n";
            } else {
                if (idType == Type.hexa) {
                    code += "\t\tldc.i4 16\n";
                } else if (idType == Type.bin) {
                    code += "\t\tldc.i4 2\n";
                }
                code += "\t\tcall int64 [mscorlib]System.Convert::ToInt64(string, int32)\n";
            }

            code += "\t\tstloc " + id + "\n";
        }
        idList.clear();
    }

    private void action36(Token token) {
        varValue = token;
    }

    private void action37(Token token) throws SemanticError {
        Type type = Type.int64;
        switch (token.getId()) {
            case 3:
                type = Type.int64;
                break;
            case 4:
                type = Type.float64;
                break;
            case 5:
                type = Type.bin;
                break;
            case 6:
                type = Type.hexa;
                break;
            case 7:
                type = Type.string;
                break;
            case 15:
            case 27:
                type = Type.bool;
                break;
        }

        for (String id:
                idList) {
            if (ST.containsKey(id)){
                throw new SemanticError(id, "já declarado", token.getPosition());
            }
            ST.put(id, type);
            Type newType = type;
            if (type == Type.bin || type == Type.hexa) newType = Type.int64;
            else if (type == Type.int64) newType = Type.float64;
            code += "\t\t.locals(" + newType.toString() + " " + id + ")\n";

            switch (token.getId()) {
                /*case 3: //int
                    code += "\t\tldc.i8 " + token.getLexeme() + "\n";
                    break;*/
                case 3: //int
                case 4: //float
                    code += "\t\tldc.r8 " + token.getLexeme() + "\n";
                    break;
                case 5: //bin
                    String bin = token.getLexeme().substring(2);
                    code += "\t\tldstr \"" + bin + "\"\n" +
                            "\t\tldc.i4 2\n" +
                            "\t\tcall int64 [mscorlib]System.Convert::ToInt64(string, int32)\n";
                    break;
                case 6: //hexa
                    String hexa = token.getLexeme().substring(2);
                    code += "\t\tldstr \"" + hexa + "\"\n" +
                            "\t\tldc.i4 16\n" +
                            "\t\tcall int64 [mscorlib]System.Convert::ToInt64(string, int32)\n";
                    break;
                case 7: //string
                    code += "\t\tldstr " + token.getLexeme() + "\n";
                    break;
                case 15:
                    code += "\t\tldc.i4.0" + "\n";
                    break;
                case 27:
                    code += "\t\tldc.i4.1" + "\n";
                    break;
                default:
                    throw new SemanticError(token.getLexeme(), "símbolo inválido", token.getPosition());
            }
            code += "\t\tstloc " + id + "\n";
        }
        idList.clear();
    }

    private void action38(Token token) { atrOperator = token; }

    private void action39() {
        int label = labelStart + 1;
        labelList.add(label);
        code += "\t\tbrfalse r" + label + "\n";
    }

    private void action40() {
        for (int i = 1; i < labelList.size(); i += 2) {
            code += "\tr" + labelList.get(i) + ":\n";
        }
        labelStart = labelList.get(labelList.size() - 1);
        labelList.clear();
    }

    private void action41() {
        int label = labelList.get(labelList.size() - 1);
        int cond = label + 1;
        labelList.add(cond);
        code += "\t\tbr r" + cond + "\n" +
                "\tr" + label + ":\n";
    }

    private void action42() {
        int label = labelList.get(labelList.size() - 1) + 1;
        labelList.add(label);
        code += "\t\tbrfalse r" + label + "\n";
    }

    private void action43() {
        int label = labelList.get(labelList.size() - 1);
        int cond = label + 1;
        labelList.add(cond);
        code += "\t\tbr r" + cond + "\n" +
                "\tr" + label + ":\n";
    }

    private void action44() {
        int label = labelStart + 1;
        labelList.add(label);
        code += "\t\tr" + label + ":\n";
    }

    private void action45() {
        int label = labelList.remove((labelList.size() - 1));
        labelStart = label;
        code += "\t\tbrfalse r" + label + "\n";
    }
}


enum Type {
    int64,
    float64,
    bool,
    hexa,
    bin,
    string
}