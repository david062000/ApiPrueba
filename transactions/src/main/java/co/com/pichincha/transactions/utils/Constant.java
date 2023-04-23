package co.com.pichincha.transactions.utils;

public class Constant {
    public static final String HEADER_PRODUCES_TRANSACTIONS = "application/vnd.transactions.v1+json";
    public static final String INITIAL_VALUE_CLIENTS_END_POINTS = "application/pichincha/clientes";
    public static final String INITIAL_VALUE_ACCOUNTS_END_POINTS = "application/pichincha/cuentas";
    public static final String INITIAL_VALUE_TRANSACTIONS_END_POINTS = "application/pichincha/movimientos";

    public static final String USE_PAGE = "http://localhost:8080";

    public static final String MESSAGE = "mensaje";
    public static final String ERROR = "error";

    public static final String CLIENT = "Cliente";
    public static final String ACCOUNT = "Cuenta";
    public static final String TRANSACTION = "Movimiento";

    public static final String CREATED_MESSAGE = "Fue creado con exito!";
    public static final String UPDATE_MESSAGE = "Ha sido actualizado con exito!";
    public static final String DELETE_MESSAGE = "Se elimino con exito!!";
    public static final String CREATED_MESSAGE_ERROR = "Error al realizar el insert en la base de datos";
    public static final String GET_MESSAGE_ERROR = "Error al realizar la consulta en la base de datos";
    public static final String NO_CONTENT_MESSAGE_ERROR = " no existe en la base de datos!";
    public static final String EDIT_MESSAGE_ERROR = "Error: no se pudo editar, el ID: ";
    public static final String DELETE_MESSAGE_ERROR = "Error al eliminar de la base de datos";
    public static final String NO_MONEY = "Saldo insuficiente!!";
}
