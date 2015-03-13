package spinat.plsqlparser;

/**
 * Created by fkarsany on 2015.03.12..
 */
public interface Visitor {

    void visit(Ast.PackageSpec packageSpec);

    void visit(Ast.PackageBody packageBody);

    void visit(Ast.Declaration declaration);

    void visit(Ast.SqlStatement sqlStatement);

    void visit(Ast.GotoStatement gotoStatement);

    void visit(Ast.CallPart callPart);

    void visit(Ast.ProcedureCall procedureCall);

    void visit(Ast.LValue lvalue);

    void visit(Ast.ExceptionHandler exceptionHandler);

    void visit(Ast.Block block);

    void visit(Ast.Ident name);

    void visit(Ast.Savepoint savepoint);

    void visit(Ast.BlockStatement blockStatement);

    void visit(Ast.ExceptionBlock exceptionBlock);

    void visit(Ast.Assignment assignment);

    void visit(Ast.Rollback rollback);

    void visit(Ast.BasicLoopStatement basicLoopStatement);

    void visit(Token token);

    void visit(Ast.SelectLoopStatement selectLoopStatement);

    void visit(Ast.WhileLoopStatement whileLoopStatement);

    void visit(Ast.Expression from);

    void visit(Ast.CursorLoopStatement cursorLoopStatement);

    void visit(Ast.FromToLoopStatement fromToLoopStatement);


    // void visit(Ast.Statement statement);

    // void visit(Ast.Expression expression);
}
