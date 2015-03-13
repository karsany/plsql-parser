package spinat.plsqlparser;

import java.math.BigDecimal;
import java.util.List;

public class Ast {

    public static class Ident {

        public final String val;

        public Ident(String val) {
            this.val = val;
        }
    }

    public static enum CmpOperator {

        LEQ, GEQ, NOT_EQ, EQ, LTH, GTH;
    }

    public static enum CreationType {

        CREATE, CREATE_OR_REPLACE
    }

    public static interface Expression extends Visitable {
    }

    public static class ActualParam implements Visitable {

        public final String name; // might be null
        public final Expression expr;

        public ActualParam(Expression expr, String name) {
            this.expr = expr;
            this.name = name;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            visitor.visit(this);
        }
    }

    public static abstract class CallPart implements Visitable {
    }

    public static class Component extends CallPart {

        public final Ident ident;

        public Component(Ident ident) {
            this.ident = ident;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(ident);
            visitor.visit(ident);
        }
    }

    public static class CallOrIndexOp extends CallPart {

        public final List<ActualParam> params;

        public CallOrIndexOp(List<ActualParam> params) {
            this.params = params;
        }

        @Override
        public void accept(Visitor visitor) {
            for (ActualParam actualParam : params)
                visitor.visit(actualParam);
            visitor.visit(this);
        }
    }

    public static class DeleteCall extends CallPart {

        public final List<Expression> exprs;

        public DeleteCall(List<Expression> exprs) {
            this.exprs = exprs;
        }

        @Override
        public void accept(Visitor visitor) {
            for (Expression expression : exprs)
                visitor.visit(expression);
            visitor.visit(this);
        }
    }

    public static class CString implements Expression {

        public final String val;

        public CString(String val) {
            this.val = val;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    // a date constant like date '2011-11-12'
    public static class CDate implements Expression {

        public final String val;

        public CDate(String val) {
            this.val = val;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class DollarDollar implements Expression {

        public final String val;

        public DollarDollar(String val) {
            this.val = val;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class CBool implements Expression {

        public final boolean val;

        public CBool(boolean val) {
            this.val = val;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class CNumber implements Expression {

        public final BigDecimal val;

        public CNumber(BigDecimal val) {
            this.val = val;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class CNull implements Expression {

        public CNull() {
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class OrExpr implements Expression {

        public final List<Expression> exprs;

        public OrExpr(List<Expression> exprs) {
            this.exprs = exprs;
        }

        @Override
        public void accept(Visitor visitor) {
            for (Expression expression : exprs)
                visitor.visit(expression);
            visitor.visit(this);
        }
    }

    public static class AndExpr implements Expression {

        public final List<Expression> exprs;

        public AndExpr(List<Expression> exprs) {
            this.exprs = exprs;
        }

        @Override
        public void accept(Visitor visitor) {
            for (Expression expression : exprs)
                visitor.visit(expression);
            visitor.visit(this);
        }
    }

    public static class NotExpr implements Expression {

        public final Expression expr;

        public NotExpr(Expression expr) {
            this.expr = expr;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            visitor.visit(this);
        }
    }

    public static class CompareExpr implements Expression {

        public final CmpOperator operator;
        public final Expression expr1;
        public final Expression expr2;

        public CompareExpr(CmpOperator operator, Expression expr1, Expression expr2) {
            this.operator = operator;
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(operator);
            visitor.visit(expr1);
            visitor.visit(expr2);
            visitor.visit(this);

        }
    }

    public static class ParenExpr implements Expression {

        public final Expression expr;

        public ParenExpr(Expression expr) {
            this.expr = expr;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            visitor.visit(this);
        }
    }

    public static class IsNullExpr implements Expression {

        public final Expression expr;
        public final boolean not;

        public IsNullExpr(Expression expr, boolean not) {
            this.expr = expr;
            this.not = not;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            visitor.visit(this);
        }
    }

    public static class LikeExpression implements Expression {

        public final Expression expr1;
        public final Expression expr2;
        public final Expression escape;
        public final boolean not;

        public LikeExpression(Expression expr1, Expression expr2, Expression escape, boolean not) {
            this.expr1 = expr1;
            this.expr2 = expr2;
            this.escape = escape;
            this.not = not;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr1);
            visitor.visit(expr2);
            visitor.visit(escape);
            visitor.visit(this);
        }
    }

    public static class BetweenExpression implements Expression {

        public final Expression expr;
        public final Expression lower;
        public final Expression upper;

        public BetweenExpression(Expression expr, Expression lower, Expression upper) {
            this.expr = expr;
            this.lower = lower;
            this.upper = upper;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            visitor.visit(lower);
            visitor.visit(upper);
            visitor.visit(this);
        }
    }

    public static final class InExpression implements Expression {

        public final Expression expr;
        public final List<Expression> set;

        public InExpression(Expression expr, List<Expression> set) {
            this.expr = expr;
            this.set = set;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            for (Expression expression : set)
                visitor.visit(expression);
            visitor.visit(this);
        }
    }

    public static final class UnaryMinusExpression implements Expression {

        public final Expression expr;

        public UnaryMinusExpression(Expression expr) {
            this.expr = expr;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            visitor.visit(this);
        }
    }

    public static final class UnaryPlusExpression implements Expression {

        public final Expression expr;

        public UnaryPlusExpression(Expression expr) {
            this.expr = expr;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            visitor.visit(this);
        }
    }

    public static enum Binop {

        ADD, MINUS, MUL, DIV, MOD, CONCAT, POWER
    }

    public static final class BinopExpression implements Expression {

        public final Binop binop;
        public final Expression expr1;
        public final Expression expr2;

        public BinopExpression(Binop binop, Expression expr1, Expression expr2) {
            this.binop = binop;
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(binop);
            visitor.visit(expr1);
            visitor.visit(expr2);
            visitor.visit(this);
        }
    }

    public static final class MultisetExpr implements Expression {

        public final String what;
        public final Expression e1;
        public final Expression e2;

        public MultisetExpr(String what, Expression e1, Expression e2) {
            this.what = what;
            this.e1 = e1;
            this.e2 = e2;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(e1);
            visitor.visit(e2);
            visitor.visit(this);
        }
    }

    public static final class ExtractDatePart implements Expression {

        public final String what;
        public final Expression expr;

        public ExtractDatePart(String what, Expression expr) {
            this.what = what;
            this.expr = expr;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            visitor.visit(this);
        }
    }

    public static final class VarOrCallExpression implements Expression {

        public final List<CallPart> callparts;

        public VarOrCallExpression(List<CallPart> callparts) {
            this.callparts = callparts;
        }

        @Override
        public void accept(Visitor visitor) {
            for (CallPart callPart : callparts)
                visitor.visit(callPart);
            visitor.visit(this);
        }
    }

    public static final class LValue implements Expression {

        public final List<CallPart> callparts;

        public LValue(List<CallPart> callparts) {
            this.callparts = callparts;
        }

        @Override
        public void accept(Visitor visitor) {
            for (CallPart callPart : callparts)
                visitor.visit(callPart);
            visitor.visit(this);
        }
    }

    public static final class SqlAttribute implements Expression {

        public final List<CallPart> attrs;

        public SqlAttribute(List<CallPart> attrs) {
            this.attrs = attrs;
        }

        @Override
        public void accept(Visitor visitor) {
            for (CallPart callPart : attrs)
                visitor.visit(callPart);
            visitor.visit(this);
        }
    }

    public static final class CursorAttribute implements Expression {

        public final List<CallPart> callparts;
        public final String attr;

        public CursorAttribute(List<CallPart> callparts, String attr) {
            this.callparts = callparts;
            this.attr = attr;
        }

        @Override
        public void accept(Visitor visitor) {
            for (CallPart callPart : callparts)
                visitor.visit(callPart);
            visitor.visit(this);
        }
    }

    //| CaseBoolExpr of (expression*expression) list * expression option
    public static class CaseExpressionPart {

        public final Expression cond;
        public final Expression result;

        public CaseExpressionPart(Expression cond, Expression result) {
            this.cond = cond;
            this.result = result;
        }
    }

    public static final class CaseBoolExpression implements Expression {

        public final List<CaseExpressionPart> cases;
        public final Expression default_;

        public CaseBoolExpression(List<CaseExpressionPart> cases, Expression default_) {
            this.cases = cases;
            this.default_ = default_;
        }

        @Override
        public void accept(Visitor visitor) {
            for (CaseExpressionPart caseExpressionPart : cases)
                visitor.visit(caseExpressionPart);
            visitor.visit(default_);
            visitor.visit(this);
        }
    }

    public static final class CaseMatchExpression implements Expression {

        public final Expression expr;
        public final List<CaseExpressionPart> matches;
        public final Expression default_;

        public CaseMatchExpression(Expression expr, List<CaseExpressionPart> matches, Expression default_) {
            this.expr = expr;
            this.matches = matches;
            this.default_ = default_;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            for (CaseExpressionPart caseExpressionPart : matches)
                visitor.visit(caseExpressionPart);
            visitor.visit(default_);
            visitor.visit(this);
        }
    }

    public static class NewExpression implements Expression {

        public final List<CallPart> callParts;

        public NewExpression(List<CallPart> callParts) {
            this.callParts = callParts;
        }

        @Override
        public void accept(Visitor visitor) {
            for (CallPart callPart : callParts)
                visitor.visit(callPart);
            visitor.visit(this);
        }
    }

    // declaration
    public static enum ParamModeType {

        IN, OUT, INOUT
    }

    public static class ParamMode {

        public final ParamModeType paramModeType;
        public final boolean nocopy;

        public ParamMode(ParamModeType paramModeType, boolean nocopy) {
            this.paramModeType = paramModeType;
            this.nocopy = nocopy;
        }
    }

    /*
     datatype datatypee =  NamedType of ident list
     | RowType of ident list
     | VarType of ident list
     | ParamterizedType of ident * int * (int option)
     (* oh well ...  *)
     | TimestampWithTimezone (* timestamp wth timezone *)
     | LongRaw (* long raw *)
     */
    public static abstract class DataType implements Visitable {
    }

    ;

    public static class NamedType extends DataType {

        public final List<Ident> idents;

        public NamedType(List<Ident> idents) {
            this.idents = idents;
        }

        @Override
        public void accept(Visitor visitor) {
            for (Ident ident : idents)
                visitor.visit(ident);
            visitor.visit(this);
        }
    }

    public static class RowType extends DataType {

        public final List<Ident> idents;

        public RowType(List<Ident> idents) {
            this.idents = idents;
        }

        @Override
        public void accept(Visitor visitor) {
            for (Ident ident : idents)
                visitor.visit(ident);
            visitor.visit(this);
        }
    }

    public static class VarType extends DataType {

        public final List<Ident> idents;

        public VarType(List<Ident> idents) {
            this.idents = idents;
        }

        @Override
        public void accept(Visitor visitor) {
            for (Ident ident : idents)
                visitor.visit(ident);
            visitor.visit(this);
        }
    }

    public static class IntervalDayToSecond extends DataType {

        public IntervalDayToSecond() {
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class IntervalYearToMonth extends DataType {

        public IntervalYearToMonth() {
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class ParameterizedType extends DataType {

        public final Ident ident;
        public final int var1;
        public final Integer var2;

        public ParameterizedType(Ident ident, int var1, Integer var2) {
            this.ident = ident;
            this.var1 = var1;
            this.var2 = var2;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(ident);
            visitor.visit(this);
        }
    }

    public static class TimestampWithTimezone extends DataType implements Visitable {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class LongRaw extends DataType implements Visitable {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    ;

    //    datatype parameter = Parameter of ident * datatypee * (param_mode option) * (expression option)
    public static class Parameter implements Visitable {

        public final Ident ident;
        public final DataType datatype;
        public final ParamMode parammode;
        public final Expression default_;

        public Parameter(Ident ident, DataType datatype, ParamMode parammode, Expression default_) {
            this.ident = ident;
            this.datatype = datatype;
            this.parammode = parammode;
            this.default_ = default_;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(ident);
            visitor.visit(datatype);
            visitor.visit(parammode);
            visitor.visit(default_);
            visitor.visit(this);
        }
    }

    /*
     datatype typedefinition = 
     RecordType of 
     {name:ident,datatyp:datatypee,notnull:bool,default:expression option} list
     | TableSimple of datatypee * bool
     | TableIndexed of datatypee * bool *datatypee
     | RefCursorType of datatypee option
     | Varray of datatypee *int * bool
     | SubType of datatypee * bool
     */
    public static abstract class TypeDefinition implements Visitable {
    }

    public static class RecordField {

        public final Ident ident;
        public final DataType datatype;
        public final boolean notnull;
        public final Expression default_;

        public RecordField(Ident ident, DataType datatype, boolean notnull, Expression default_) {
            this.ident = ident;
            this.datatype = datatype;
            this.notnull = notnull;
            this.default_ = default_;
        }
    }

    public static class RecordType extends TypeDefinition {

        List<RecordField> fields;

        public RecordType(List<RecordField> fields) {
            this.fields = fields;
        }

        @Override
        public void accept(Visitor visitor) {
            for (RecordField ident : fields)
                visitor.visit(ident);
            visitor.visit(this);
        }
    }

    public static class TableSimple extends TypeDefinition {

        public final DataType datatype;
        public final boolean notnull;

        public TableSimple(DataType datatype, boolean notnull) {
            this.datatype = datatype;
            this.notnull = notnull;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(datatype);
            visitor.visit(this);
        }
    }

    public static class TableIndexed extends TypeDefinition {

        public final DataType datatype;
        public final boolean notnull;
        public final DataType indextype;

        public TableIndexed(DataType datatype, boolean notnull, DataType indextype) {
            this.datatype = datatype;
            this.notnull = notnull;
            this.indextype = indextype;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(datatype);
            visitor.visit(indextype);
            visitor.visit(this);
        }
    }

    public static class RefCursorType extends TypeDefinition {

        public final DataType datatype; // option

        public RefCursorType(DataType datatype) {
            this.datatype = datatype;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(datatype);
            visitor.visit(this);
        }
    }

    public static class Varray extends TypeDefinition {

        public final DataType datatype;
        public final int size;
        public boolean notnull;

        public Varray(DataType datatype, int size, boolean notnull) {
            this.datatype = datatype;
            this.size = size;
            this.notnull = notnull;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(datatype);
            visitor.visit(this);
        }
    }

    public static class SubType extends TypeDefinition {

        public final DataType datatype;
        public final boolean notnull;
        public final T2<Integer, Integer> range;

        public SubType(DataType datatype, T2<Integer, Integer> range, boolean notnull) {
            this.datatype = datatype;
            this.range = range;
            this.notnull = notnull;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(datatype);
            visitor.visit(this);
        }
    }

    public static class FunctionHeading {

        public final Ident name;
        public final List<Parameter> parameters;
        public final DataType returntype;
        public final List<String> attributes;

        public FunctionHeading(Ident name, List<Parameter> parameters, DataType returntype, List<String> attributes) {
            this.name = name;
            this.parameters = parameters;
            this.returntype = returntype;
            this.attributes = attributes;
        }
    }

    public static class ProcedureHeading implements Visitable {

        public final Ident name;
        public final List<Parameter> parameters;

        public ProcedureHeading(Ident name, List<Parameter> parameters) {
            this.name = name;
            this.parameters = parameters;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            for (Parameter parameter : parameters)
                visitor.visit(parameter);
            visitor.visit(this);
        }
    }

    /*
     and declaration = FunctionDeclaration of function_heading
     | TypeDefinition of ident * typedefinition
     | ProcedureDeclaration of procedure_heading
     | ExceptionDeclaration of ident
     | VariableDeclaration of ident * datatypee * bool * bool * (expression option)
     | Pragma of ident * (Tokens.token list)
     | FunctionDefinition of function_heading * block
     | ProcedureDefinition of procedure_heading * block 
     (*  language java name 'assasasasa' *)
     | LFunctionDefinition of function_heading * string * string
     | LProcedureDefinition of procedure_heading * string * string 
     | CursorDefinition of ident *  (parameter list) * (Tokens.token list)
     */
    public static interface Declaration extends Visitable {
    }

    public static class TypeDeclaration implements Declaration {

        public final Ident name;
        public final TypeDefinition typedefinition;

        public TypeDeclaration(Ident name, TypeDefinition typedefinition) {
            this.name = name;
            this.typedefinition = typedefinition;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            visitor.visit(typedefinition);
        }
    }

    public static class FunctionDeclaration implements Declaration {

        public final FunctionHeading functionheading;

        public FunctionDeclaration(FunctionHeading functionheading) {
            this.functionheading = functionheading;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(functionheading);
        }
    }

    public static class ProcedureDeclaration implements Declaration {

        public final ProcedureHeading procedureheading;

        public ProcedureDeclaration(ProcedureHeading procedureheading) {
            this.procedureheading = procedureheading;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(procedureheading);
        }
    }

    public static class ExceptionDeclaration implements Declaration {

        public final Ident name;

        public ExceptionDeclaration(Ident name) {
            this.name = name;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
        }
    }

    /*
     public static class CursorDeclaration extends Declaration {
     public final Ident name;
     public List<Parameter> parameters;
     public List<Token> sql;
     public CursorDeclaration(Ident name,List<Parameter> parameters,List<Token> sql) {
     this.name = name;
     this.parameters = parameters;
     this.sql = sql;
     }
     }
     */
    // VariableDeclaration of ident * datatypee * bool * bool * (expression option)
    public static class VariableDeclaration implements Declaration {

        public final Ident name;
        public final DataType datatype;
        public final boolean notnull;
        public final boolean constant;
        public final Expression default_; // option

        public VariableDeclaration(Ident name, DataType datatype, boolean notnull,
                                   boolean constant, Expression default_) {
            this.name = name;
            this.datatype = datatype;
            this.notnull = notnull;
            this.constant = constant;
            this.default_ = default_;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
        }
    }

    public static abstract class Pragma implements Declaration {
    }

    ;

    public static class SimplePragma extends Pragma {// of ident * (Tokens.token list)

        public final Ident name;
        public final List<Token> what;

        public SimplePragma(Ident name, List<Token> what) {
            this.name = name;
            this.what = what;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            for (Token token : what)
                visitor.visit(token);
            visitor.visit(this);
        }
    }

    public static class PragmaRestrictReferences implements Declaration {

        public final Ident name;
        public final boolean default_;
        public final List<String> modes;

        public PragmaRestrictReferences(Ident name, boolean default_,
                                        List<String> modes) {
            if (name == null && default_ || name != null && !default_) {
                this.name = name;
                this.default_ = default_;
                this.modes = modes;
            } else {
                throw new RuntimeException("BUG");
            }
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            visitor.visit(this);
        }
    }

    public static class Block implements Visitable {

        public final List<Declaration> declarations;
        public final List<Statement> statements;
        public final ExceptionBlock exceptionBlock;

        public Block(List<Declaration> declarations,
                     List<Statement> statements,
                     ExceptionBlock exceptionBlock) {
            this.declarations = declarations;
            this.statements = statements;
            this.exceptionBlock = exceptionBlock;
        }


        @Override
        public void accept(Visitor visitor) {
            for (Declaration declaration : declarations)
                visitor.visit(declaration);
            for (Statement statement : statements)
                visitor.visit(statement);
            visitor.visit(exceptionBlock);
        }
    }

    public static class FunctionDefinition implements Declaration {

        public final FunctionHeading functionheading;
        public final Block block;

        public FunctionDefinition(FunctionHeading functionheading, Block block) {
            this.functionheading = functionheading;
            this.block = block;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(functionheading);
            visitor.visit(block);
            visitor.visit(this);
        }
    }

    public static class ProcedureDefinition implements Declaration {

        public final ProcedureHeading procedureheading;
        public final Block block;

        public ProcedureDefinition(ProcedureHeading procedureheading, Block block) {
            this.procedureheading = procedureheading;
            this.block = block;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(procedureheading);
            visitor.visit(block);
            visitor.visit(this);
        }
    }

    //| LFunctionDefinition of function_heading * string * string
    //           | LProcedureDefinition of procedure_heading * string * string 
    public static class ExtFunctionDefinition implements Declaration {

        public final FunctionHeading functionheading;
        public final String language;
        public final String signature;

        public ExtFunctionDefinition(FunctionHeading functionheading, String language, String signature) {
            this.functionheading = functionheading;
            this.language = language;
            this.signature = signature;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(functionheading);
            visitor.visit(this);
        }
    }

    public static class ExtProcedureDefinition implements Declaration {

        public final ProcedureHeading procedureheading;
        public final String language;
        public final String signature;

        public ExtProcedureDefinition(ProcedureHeading procedureheading, String language, String signature) {
            this.procedureheading = procedureheading;
            this.language = language;
            this.signature = signature;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(procedureheading);
            visitor.visit(this);
        }
    }

    //of ident *  (parameter list) * (Tokens.token list)
    public static class CursorDefinition implements Declaration {

        public final Ident name;
        public final List<Parameter> parameters;
        public final List<Token> sql;

        public CursorDefinition(Ident name, List<Parameter> parameters, List<Token> sql) {
            this.name = name;
            this.parameters = parameters;
            this.sql = sql;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            for (Parameter parameter : parameters)
                visitor.visit(parameter);
            for (Token token : sql)
                visitor.visit(token);
            visitor.visit(this);
        }
    }

    /*
    
     and declarations = Declarations of (declaration * int * int) list
     type object_name = ident option * ident

     datatype package_body = PackageBody of
     {packageName: object_name,
     declarations: declarations,
     body: (statements * exception_handler list) option}

     datatype package_spec = PackageSpec of 
     {packageName : object_name, invokerClause : string option, declarations:declaration
     */
    public static class ObjectName {

        public final Ident owner; // option
        public final Ident name;

        public ObjectName(Ident owner, Ident name) {
            this.owner = owner;
            this.name = name;
        }
    }

    public static class PackageSpec implements Visitable {

        public final ObjectName objectname;
        public final List<Declaration> declarations;
        public final String invokerclause;

        public PackageSpec(ObjectName objectname, List<Declaration> declarations,
                           String invokerclause) {
            this.declarations = declarations;
            this.objectname = objectname;
            this.invokerclause = invokerclause;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class QualId {

        public final List<Ident> idents;

        public QualId(List<Ident> idents) {
            this.idents = idents;
        }
    }

    public static interface Statement extends Visitable {
    }

    public static class NullStatement implements Statement {

        @Override
        public void accept(Visitor visitor) {

        }
    }

    public static class GotoStatement implements Statement {

        public final String label;

        public GotoStatement(String label) {
            this.label = label;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class SqlStatement implements Statement {

        public final List<Token> tokens;

        public SqlStatement(List<Token> tokens) {
            this.tokens = tokens;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();
            for (Token t : tokens) {
                ret.append(t.str + " ");
            }
            return ret.toString();
        }
    }

    public static class ProcedureCall implements Statement {

        public final List<CallPart> callparts;

        public ProcedureCall(List<CallPart> callparts) {
            this.callparts = callparts;
        }

        @Override
        public void accept(Visitor visitor) {
            for (CallPart callPart : callparts)
                visitor.visit(callPart);
            visitor.visit(this);
        }
    }

    public static class Assignment implements Statement {

        public final LValue lvalue;
        public final Expression expression;

        public Assignment(LValue lvalue, Expression expression) {
            this.lvalue = lvalue;
            this.expression = expression;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(lvalue);
            visitor.visit(expression);
            visitor.visit(this);
        }
    }

    public static class ExceptionHandler {

        public final List<QualId> exceptions;
        public final List<Statement> statements;

        public ExceptionHandler(List<QualId> exceptions,
                                List<Statement> statements) {
            this.exceptions = exceptions;
            this.statements = statements;
        }
    }

    public static class ExceptionBlock implements Visitable {

        public final List<ExceptionHandler> handlers;
        public final List<Statement> othershandler;

        public ExceptionBlock(List<ExceptionHandler> handlers,
                              List<Statement> othershandler) {
            this.handlers = handlers;
            this.othershandler = othershandler;
        }

        @Override
        public void accept(Visitor visitor) {
            for (ExceptionHandler exceptionHandler : handlers) {
                visitor.visit(exceptionHandler);
            }

            for (Statement statement : othershandler)
                visitor.visit(statement);

            visitor.visit(this);
        }
    }

    public static class BlockStatement implements Statement {

        public final Block block;

        public BlockStatement(Block block) {
            this.block = block;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(block);
            visitor.visit(this);
        }
    }

    public static class Savepoint implements Statement {

        public final Ident name;

        public Savepoint(Ident name) {
            this.name = name;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            visitor.visit(this);
        }
    }

    public static class Rollback implements Statement {

        public final Ident name;

        public Rollback(Ident name) {
            this.name = name;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            visitor.visit(this);
        }
    }

    public static class BasicLoopStatement implements Statement {

        public final List<Ast.Statement> statements;

        public BasicLoopStatement(List<Ast.Statement> statements) {
            this.statements = statements;
        }

        @Override
        public void accept(Visitor visitor) {
            for (Statement statement : statements)
                visitor.visit(statement);
            visitor.visit(this);
        }
    }

    public static class SelectLoopStatement implements Statement {

        public final Ident iterator;
        public final List<Token> sql;
        public final List<Statement> statements;

        public SelectLoopStatement(Ident iterator, List<Token> sql, List<Statement> statements) {
            this.iterator = iterator;
            this.sql = sql;
            this.statements = statements;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(iterator);
            for (Token token : sql)
                visitor.visit(token);
            for (Statement statement : statements)
                visitor.visit(statement);
            visitor.visit(this);
        }
    }

    public static class WhileLoopStatement implements Statement {

        public final Expression condition;
        public final List<Statement> statements;

        public WhileLoopStatement(Expression condition, List<Statement> statements) {
            this.condition = condition;
            this.statements = statements;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(condition);
            for (Statement statement : statements)
                visitor.visit(statement);

            visitor.visit(this);
        }
    }

    public static class FromToLoopStatement implements Statement {

        public final Ident iterator;
        public final boolean reverse;
        public final Expression from;
        public final Expression to;
        public final List<Statement> statements;

        public FromToLoopStatement(Ident iterator,
                                   boolean reverse,
                                   Expression from,
                                   Expression to,
                                   List<Statement> statements) {
            this.iterator = iterator;
            this.reverse = reverse;
            this.from = from;
            this.to = to;
            this.statements = statements;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(iterator);
            visitor.visit(from);
            visitor.visit(to);
            for (Statement statement : statements)
                visitor.visit(statement);
            visitor.visit(this);
        }
    }

    public static class CursorLoopStatement implements Statement {

        public final Ident iterator;
        public final Expression expr;
        public final List<Statement> statements;

        public CursorLoopStatement(Ident iterator,
                                   Expression expr,
                                   List<Statement> statements) {
            this.iterator = iterator;
            this.expr = expr;
            this.statements = statements;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(iterator);
            visitor.visit(expr);
            for (Statement statement : statements)
                visitor.visit(statement);
            visitor.visit(this);
        }
    }

    public static class ExprAndStatements {

        public final Expression expr;
        public final List<Statement> statements;

        public ExprAndStatements(Expression expr, List<Statement> statements) {
            this.expr = expr;
            this.statements = statements;
        }
    }

    public static class CaseMatchStatement implements Statement {

        public final Expression match;
        public final List<ExprAndStatements> branches;
        public final List<Statement> defaultbranch;

        public CaseMatchStatement(Expression match, List<ExprAndStatements> branches, List<Statement> defaultbranch) {
            this.match = match;
            this.branches = branches;
            this.defaultbranch = defaultbranch;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(match);
            for (ExprAndStatements exprAndStatements : branches)
                visitor.visit(exprAndStatements);
            for (Statement statement : defaultbranch)
                visitor.visit(statement);
            visitor.visit(this);
        }
    }

    public static class CaseCondStatement implements Statement {

        public final List<ExprAndStatements> branches;
        public final List<Statement> defaultbranch;

        public CaseCondStatement(List<ExprAndStatements> branches, List<Statement> defaultbranch) {
            this.branches = branches;
            this.defaultbranch = defaultbranch;
        }

        @Override
        public void accept(Visitor visitor) {
            for (ExprAndStatements exprAndStatements : branches)
                visitor.visit(exprAndStatements);

            for (Statement statement : defaultbranch)
                visitor.visit(statement);

            visitor.visit(this);
        }
    }

    public static class IfStatement implements Statement {

        public final List<ExprAndStatements> branches;
        public final List<Statement> elsebranch;

        public IfStatement(List<ExprAndStatements> branches, List<Statement> elsebranch) {
            this.branches = branches;
            this.elsebranch = elsebranch;
        }

        @Override
        public void accept(Visitor visitor) {
            for (ExprAndStatements exprAndStatements : branches)
                visitor.visit(exprAndStatements);

            for (Statement statement : elsebranch)
                visitor.visit(statement);

            visitor.visit(this);
        }
    }

    public static class RaiseStatement implements Statement {

        public final QualId name;

        public RaiseStatement(QualId name) {
            this.name = name;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            visitor.visit(this);
        }
    }

    public static class ReturnStatement implements Statement {

        public final Expression expr;

        public ReturnStatement(Expression expr) {
            this.expr = expr;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(expr);
            visitor.visit(this);
        }
    }

    public static class OpenFixedCursorStatement implements Statement {

        public final QualId name;
        public final List<ActualParam> params;

        public OpenFixedCursorStatement(QualId name, List<ActualParam> params) {
            this.name = name;
            this.params = params;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            for (ActualParam actualParam : params)
                visitor.visit(actualParam);
            visitor.visit(this);
        }
    }

    public static class OpenStaticRefCursorStatement implements Statement {

        public final QualId name;
        public final List<Token> sql;

        public OpenStaticRefCursorStatement(QualId name, List<Token> sql) {
            this.name = name;
            this.sql = sql;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            for (Token token : sql)
                visitor.visit(token);
            visitor.visit(this);
        }
    }

    public static class OpenDynamicRefCursorStatement implements Statement {

        public final QualId name;
        public final Expression sqlexpr;
        public final List<Expression> bindargs;

        public OpenDynamicRefCursorStatement(QualId name, Expression sqlexpr, List<Expression> bindargs) {
            this.name = name;
            this.sqlexpr = sqlexpr;
            this.bindargs = bindargs;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            visitor.visit(sqlexpr);
            for (Expression expression : bindargs)
                visitor.visit(expression);
            visitor.visit(this);
        }
    }

    public static class CloseStatement implements Statement {

        public final QualId name;

        public CloseStatement(QualId name) {
            this.name = name;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            visitor.visit(this);
        }
    }

    public static class FetchStatement implements Statement {

        public final QualId name;
        public final List<LValue> lvalues;
        public final boolean bulkcollect;
        public final Expression limit;

        public FetchStatement(QualId name, List<LValue> lvalues, boolean bulkcollect, Expression limit) {
            this.name = name;
            this.lvalues = lvalues;
            this.bulkcollect = bulkcollect;
            this.limit = limit;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(name);
            for (LValue lValue : lvalues)
                visitor.visit(lValue);
            visitor.visit(limit);
            visitor.visit(this);
        }
    }

    public static class ExitStatement implements Statement {

        public final Ident scopename;
        public final Expression condition;

        public ExitStatement(Ident scopename, Expression condition) {
            this.scopename = scopename;
            this.condition = condition;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(scopename);
            visitor.visit(condition);
            visitor.visit(this);
        }
    }

    public static class ContinueStatement implements Statement {

        public final Ident scopename;
        public final Expression condition;

        public ContinueStatement(Ident scopename, Expression condition) {
            this.scopename = scopename;
            this.condition = condition;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(scopename);
            visitor.visit(condition);
            visitor.visit(this);
        }
    }

    public static class PipeRowStatement implements Statement {

        public final List<Expression> expressions;

        public PipeRowStatement(List<Expression> expressions) {
            this.expressions = expressions;
        }

        @Override
        public void accept(Visitor visitor) {
            for (Expression expression : expressions)
                visitor.visit(expression);
            visitor.visit(this);
        }
    }

    /*expression (* the sql string *)
     * bool (* bulk into *)
     * lvalue list (* the into lalues *)
     * ((param_mode * expression) list) (* the using parameters *)
     */
    public static class ExecuteImmediateParameter implements Visitable {

        public final ParamModeType parammode;
        public final Expression value;

        public ExecuteImmediateParameter(ParamModeType paramMode, Expression value) {
            this.value = value;
            this.parammode = paramMode;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(parammode);
            visitor.visit(value);
        }


    }

    public static class ExecuteImmediateInto implements Statement {

        public final Expression sqlexpr;
        public final boolean bulk;
        public final List<LValue> intovalues;
        public final List<ExecuteImmediateParameter> usingparameters;

        public ExecuteImmediateInto(Expression sqlexpr, boolean bulk,
                                    List<LValue> intovalues,
                                    List<ExecuteImmediateParameter> usingparameters) {
            this.sqlexpr = sqlexpr;
            this.bulk = bulk;
            this.intovalues = intovalues;
            this.usingparameters = usingparameters;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(sqlexpr);
            for (LValue lValue : intovalues)
                visitor.visit(lValue);

            for (ExecuteImmediateParameter executeImmediateParameter : usingparameters)
                visitor.visit(executeImmediateParameter);

            visitor.visit(this);
        }
    }

    /*
     datatype bounds_clause = FromTo of expression *expression
     | Indices of expression * (expression * expression) option
     | Values of expression
     */
    public static abstract class BoundsClause {
    }

    public static class FromToBounds extends BoundsClause {

        public final Expression from;
        public final Expression to;

        public FromToBounds(Expression from, Expression to) {
            this.from = from;
            this.to = to;
        }
    }

    /*| ForAllStatement of ident * bounds_clause *  (Tokens.token list)*/
    public static class ForAllStatement implements Statement {

        public final Ident variable;
        public final BoundsClause bounds;
        public final List<Token> sqloderso;

        public ForAllStatement(Ident variable, BoundsClause bounds, List<Token> sqloderso) {
            this.variable = variable;
            this.bounds = bounds;
            this.sqloderso = sqloderso;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(variable);
            visitor.visit(bounds);
            for (Token token : sqloderso)
                visitor.visit(this);
        }
    }

    /*
     expression (* the sq statement *)
     * (param_mode * expression) list (* the using variables *)
     * expression list (* the returned values *)
     * (lvalue list) (* the returning lValues *)
     */
    public static class ExecuteImmediateDML implements Statement {

        public final Expression sqlexpr;
        public final List<ExecuteImmediateParameter> usingparameters;
        public final List<Ident> returnedvalues;
        public final List<LValue> returnedinto;

        public ExecuteImmediateDML(
                Expression sqlexpr,
                List<ExecuteImmediateParameter> usingparameters,
                List<Ident> returnedvalues,
                List<LValue> returnedinto) {
            this.sqlexpr = sqlexpr;
            this.usingparameters = usingparameters;
            this.returnedvalues = returnedvalues;
            this.returnedinto = returnedinto;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(sqlexpr);
            for (ExecuteImmediateParameter executeImmediateParameter : usingparameters)
                visitor.visit(executeImmediateParameter);
            for (Ident ident : returnedvalues)
                visitor.visit(ident);
            for (LValue lValue : returnedinto)
                visitor.visit(lValue);
            visitor.visit(this);
        }
    }

    public static class PackageBody implements Visitable {

        public final ObjectName name;
        public final List<Declaration> declarations;
        public final List<Ast.Statement> statements;
        public final ExceptionBlock exceptionBlock;

        public PackageBody(ObjectName name, List<Declaration> declarations,
                           List<Statement> statements,
                           ExceptionBlock exceptionBlock) {
            this.name = name;
            this.declarations = declarations;
            this.statements = statements;
            this.exceptionBlock = exceptionBlock;
        }

        @Override
        public void accept(Visitor visitor) {
            for (Declaration declaration : declarations) {
                visitor.visit(declaration);
            }
            if (statements != null)
                for (Statement statement : statements) {

                    visitor.visit(statement);
                }
            visitor.visit(this);
        }
    }

    //OpenStaticRefCursorStatement of ident list * (Tokens.token list)
    //         | OpenDynamicRefCursorStatement of ident list * expression * expression list

    /*
     | PLSQLBlock of block
     | CaseStatementMatch of expression 
     * (expression * statements ) list 
     * (statements option)
     | CaseStatementCond of (expression * statements) list * (statements option)
     | BasicLoopStatement of statements
     | SelectLoopStatement of ident * Tokens.token list * statements
     | WhileLoopStatement of expression * statements
     | FromToLoopStatement of ident * bool * expression * expression * statements
     | InCursorLoopStatement of ident * expression * statements
     | CursorLoopStatement of ident * expression * statements
     | ForAllStatement of ident * bounds_clause *  (Tokens.token list)
     | SQLStatement of  (Tokens.token list) 
     | ReturnStatement of expression option
     | RaiseStatement of ident list option
     | OpenFixedCursorStatement of ident list * param list
     | OpenStaticRefCursorStatement of ident list * (Tokens.token list)
     | OpenDynamicRefCursorStatement of ident list * expression * expression list
     | IfStatement of (expression * statements) list * statements option
     | ExcuteImmediateSelect of 
     expression (* the sql string *)
     * bool (* bulk into *)
     * lvalue list (* the into lalues *)
     * ((param_mode * expression) list) (* the using parameters *)
     | ExcuteImmediateDML of 
     expression (* the sq statement *)
     * (param_mode * expression) list (* the using variables *)
     * expression list (* the returned values *)
     * (lvalue list) (* the returning lValues *)
     | GotoStatement of ident
     | FetchStatement of qualid * lvalue list
     | BulkFetchStatement of qualid * lvalue list * (expression option)
     | ExitStatement of ident option * expression option
     | ContinueStatement of ident option * expression option
     | CloseStatement of qualid
     | LabeledStatement of ident * (statement * int * int)
     | PipeRowStatement of expression list
     | Savepoint of ident
     | Rollback of ident option
     (* commit is missing, but it is interpreted as a procedure *)
     */
    /*and declaration = FunctionDeclaration of function_heading
     | TypeDefinition of ident * typedefinition
     | ProcedureDeclaration of procedure_heading
     | ExceptionDeclaration of ident
     | VariableDeclaration of ident * datatypee * bool * bool * (expression option)
     | Pragma of ident * (Tokens.token list)
     | FunctionDefinition of function_heading * block
     | ProcedureDefinition of procedure_heading * block 
     (*  language java name 'assasasasa' *)
     | LFunctionDefinition of function_heading * string * string
     | LProcedureDefinition of procedure_heading * string * string 
     | CursorDefinition of ident *  (parameter list) * (Tokens.token list)*/
}
