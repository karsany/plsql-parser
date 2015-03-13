package spinat.plsqlparser;

/**
 * Created by fkarsany on 2015.03.12..
 */
public interface Visitor {


    void visit(Ast.Ident ident);

    void visit(Ast.ActualParam actualParam);

    void visit(Ast.CallOrIndexOp callOrIndexOp);

    void visit(Ast.DeleteCall deleteCall);

    void visit(Ast.CString cString);

    void visit(Ast.CDate cDate);

    void visit(Ast.DollarDollar dollarDollar);

    void visit(Ast.CBool cBool);

    void visit(Ast.CNumber cNumber);

    void visit(Ast.CNull cNull);

    void visit(Ast.CmpOperator operator);

    void visit(Ast.CompareExpr compareExpr);

    void visit(Ast.LikeExpression likeExpression);

    void visit(Ast.Expression expr);

    void visit(Ast.Binop binop);

    void visit(Ast.CallPart callPart);

    void visit(Ast.CaseExpressionPart caseExpressionPart);

    void visit(Ast.TimestampWithTimezone timestampWithTimezone);

    void visit(Ast.LongRaw longRaw);

    void visit(Ast.FunctionHeading functionheading);

    void visit(Ast.ProcedureHeading procedureheading);

    void visit(Ast.PackageSpec packageSpec);

    void visit(Ast.GotoStatement gotoStatement);

    void visit(Ast.SqlStatement sqlStatement);

    void visit(Ast.ProcedureCall procedureCall);

    void visit(Ast.Assignment assignment);

    void visit(Ast.ExceptionHandler exceptionHandler);

    void visit(Ast.ExceptionBlock exceptionBlock);

    void visit(Ast.Block block);

    void visit(Ast.BlockStatement blockStatement);

    void visit(Ast.Savepoint savepoint);

    void visit(Ast.Rollback rollback);

    void visit(Ast.BasicLoopStatement basicLoopStatement);

    void visit(Token token);

    void visit(Ast.ExprAndStatements exprAndStatements);

    void visit(Ast.QualId name);

    void visit(Ast.ParamModeType parammode);

    void visit(Ast.ExecuteImmediateParameter executeImmediateParameter);

    void visit(Ast.BoundsClause bounds);

    void visit(Ast.ExecuteImmediateDML executeImmediateDML);

    void visit(Ast.NamedType namedType);

    void visit(Ast.RowType rowType);

    void visit(Ast.VarType varType);

    void visit(Ast.IntervalDayToSecond intervalDayToSecond);

    void visit(Ast.ParameterizedType parameterizedType);

    void visit(Ast.DataType datatype);

    void visit(Ast.ParamMode parammode);

    void visit(Ast.Parameter parameter);

    void visit(Ast.RecordField ident);

    void visit(Ast.RecordType recordType);

    void visit(Ast.TableSimple tableSimple);

    void visit(Ast.RefCursorType refCursorType);

    void visit(Ast.Varray varray);

    void visit(Ast.SubType subType);

    void visit(Ast.TypeDefinition typedefinition);

    void visit(Ast.SimplePragma simplePragma);

    void visit(Ast.PragmaRestrictReferences pragmaRestrictReferences);

    void visit(Ast.FunctionDefinition functionDefinition);

    void visit(Ast.ProcedureDefinition procedureDefinition);

    void visit(Ast.ExtFunctionDefinition extFunctionDefinition);

    void visit(Ast.ExtProcedureDefinition extProcedureDefinition);

    void visit(Ast.CursorDefinition cursorDefinition);

    void visit(Ast.WhileLoopStatement whileLoopStatement);

    void visit(Ast.SelectLoopStatement selectLoopStatement);

    void visit(Ast.FromToLoopStatement fromToLoopStatement);

    void visit(Ast.CursorLoopStatement cursorLoopStatement);

    void visit(Ast.CaseMatchStatement caseMatchStatement);

    void visit(Ast.CaseCondStatement caseCondStatement);

    void visit(Ast.IfStatement ifStatement);

    void visit(Ast.RaiseStatement raiseStatement);

    void visit(Ast.ReturnStatement returnStatement);

    void visit(Ast.OpenFixedCursorStatement openFixedCursorStatement);

    void visit(Ast.OpenStaticRefCursorStatement openStaticRefCursorStatement);

    void visit(Ast.OpenDynamicRefCursorStatement openDynamicRefCursorStatement);

    void visit(Ast.CloseStatement closeStatement);

    void visit(Ast.FetchStatement fetchStatement);

    void visit(Ast.ExitStatement exitStatement);

    void visit(Ast.ContinueStatement continueStatement);

    void visit(Ast.PipeRowStatement pipeRowStatement);

    void visit(Ast.ExecuteImmediateInto executeImmediateInto);

    void visit(Ast.ForAllStatement forAllStatement);

    void visit(Ast.PackageBody packageBody);


    void visit(Ast.Statement statement);

    void visit(Ast.Declaration declaration);
}
