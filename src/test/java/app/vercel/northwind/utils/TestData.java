package app.vercel.northwind.utils;

public class TestData {

    /**   E-MAILS E SENHAS  LOGIN*/
    public static final String EMAIL_VALIDO = "admin@qatest.com";
    public static final String EMAIL_INVALIDO = "teste.com";
    public static final String EMAIL_INEXISTENTE = "qa@qa.com";
    public static final String EMAIL_VAZIO = "";

    public static final String PASSWORD_VAZIO = "";
    public static final String PASSWORD_CURTO = "Te@14";
    public static final String PASSWORD_INVALIDO = "Teste@321";
    public static final String PASSWORD_VALIDO = "Teste@123";

    /**   E-MAILS E SENHAS  FORM */
    public static final String PASSWORD_SEM_LETRA_MAIUSCULA = "teste@123";
    public static final String PASSWORD_SEM_LETRA_MINUSCULA = "TESTE@123";
    public static final String PASSWORD_SEM_CARACTER_ESPECIAL = "Teste0123";
    public static final String PASSWORD_CARACTER_ESPECIAL_REPETIDO = "Teste@123@@";


    /**   MENSAGENS LOGIN */

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

    /** MENSAGEM FORMULÁRIO LOGIN*/

    public static final String MSG_NOME_TAMANHO_MAXIMO =
            "Nome deve ter no máximo 100 caracteres";
    public static final String MSG_NOME_TAMANHO_MINIMO =
            "Nome deve ter no mínimo 3 caracteres";
    public static final String MSG_NOME_OBRIGATORIO =
            "Nome completo é obrigatório";
    public static final String MSG_NOME_APENAS_LETRAS_ESPACOS =
            "Nome deve conter apenas letras e espaços";

    public static final String MSG_EMAIL_OBRIGATORIO =
            "E-mail é obrigatório";
    public static final String MSG_EMAIL_INVALIDO =
            "E-mail inválido";
    public static final String MSG_EMAIL_TAMANHO_MAXIMO =
            "E-mail deve ter no máximo 255 caracteres";

    public static final String MSG_SENHA_TAMANHO_MINIMO =
            "Senha deve ter no mínimo 8 caracteres";
    public static final String MSG_SENHA_CONTER_LETRA_MINUSCULA =
            "Senha deve ter pelo menos uma letra minúscula";
    public static final String MSG_SENHA_CONTER_LETRA_MAIUSCULA =
            "Senha deve ter pelo menos uma letra maiúscula";
    public static final String MSG_SENHA_CONTER_CARACTERE_ESPECIAL =
            "Senha deve ter pelo menos um caractere especial (@$!%*?&)";
    public static final String MSG_SENHA_OBRIGATORIA =
            "Senha é obrigatória";
    public static final String MSG_SENHA_CARACTER_REPETIDO =
            "Senha não pode ter 3 ou mais caracteres repetidos";
    public static final String MSG_SENHA_TAMANHO_MAXIMO=
            "Senha deve ter no máximo 128 caracteres";

    public static final String MSG_CONFIRM_SENHA_DIVERGENTE =
            "Senhas não conferem";
    public static final String MSG_CONFIRM_SENHA_OBRIGATORIA =
            "Confirmação de senha é obrigatória";


    /** MENSAGEM CATEGORIA*/
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
