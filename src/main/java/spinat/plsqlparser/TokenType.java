package spinat.plsqlparser;

public enum TokenType {

    EOLineComment,
    String,
    MultiLineComment,
    QIdent,
    Ident,
    LParen,
    RParen,
    LBracket,
    RBracket,
    Assign,
    Comma,
    Semi,
    Percent,
    Arrow,
    Equal,
    NEqual,
    LEqual,
    GEqual,
    Greater,
    Less,
    Plus,
    Minus,
    DotDot,
    Dot,
    Div,
    Mul,
    StringAdd,
    WhiteSpace,
    Int,
    Power,
    LabelStart,
    LabelEnd,
    Colon,
    TheEnd,
    Exclamation,
    Float,
    QString,
    DollarDollarIdent
}