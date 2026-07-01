package app.vercel.northwind.utils;

public class TestData {

    /**   E-MAILS E SENHAS */
    public static final String EMAIL_VALIDO = "admin@qatest.com";
    public static final String EMAIL_INVALIDO = "teste.com";
    public static final String EMAIL_INEXISTENTE = "teste@teste.com";
    public static final String EMAIL_VAZIO = "";

    public static final String PASSWORD_VAZIO = "";
    public static final String PASSWORD_CURTO = "Te@14";
    public static final String PASSWORD_INVALIDO = "Teste@321";
    public static final String PASSWORD_VALIDO = "Teste@123";

    /**   MENSAGENS */

    public static final String MSG_SENHA_CURTA =
            "Senha deve ter pelo menos 6 caracteres";

    public static final String MSG_CAMPOS_OBRIGATORIOS =
            "Email e senha são obrigatórios";

    public static final String MSG_FORMATO_EMAIL_INVALIDO =
            "Formato de email inválido. Use: nome@dominio.com";

    public static final String MSG_USUARIO_NAO_ENCONTRADO =
            "Usuário não encontrado. Verifique o email ou cadastre-se.";

    public static final String MSG_EMAIL_SENHA_INVALIDOS =
            "Email ou senha inválidos";

    public static final String MSG_NOME_CATEGORIA_OBRIGATORIO =
            "Nome da categoria é obrigatório";

    public static final String MSG_DESCRICAO_OBRIGATORIA =
            "Descrição é obrigatória";

    public static final String MSG_NOME_INVALIDO =
            "Deve ter entre 2 e 50 caracteres";

    public static final String MSG_DESCRICAO_INVALIDA =
            "Deve ter entre 10 e 200 caracteres";

    public static final String MSG_CATEGORIA_CADASTRADA =
            "Categoria cadastrada com sucesso!";

    public static final String MSG_USUARIO_SEM_TOKEN =
            "Você precisa estar logado";
}
