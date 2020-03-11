package tech.armsys.reservaciones.controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class alertas {

    public static Optional mostrarAlerta(String tipoAlerta, String subtipo, String titulo, String encabezado, String contenido){
        Optional<ButtonType> result = Optional.empty();

        if (tipoAlerta.equals("error")){
                if(subtipo.equals("credenciales")){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error de validación");
                    error.setHeaderText("Credenciales incorrectas");
                    error.setContentText("Por favor verifique sus credenciales e intente nuevamente");
                    result = error.showAndWait();
                    }
                if(subtipo.equals("BD")){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error en la base de datos");
                    error.setHeaderText("No se ha podido conectar con la base de datos correctamente");
                    error.setContentText("Verifique que la base de datos esté inicializada y funcionando correctamente. \n"+contenido);
                    result = error.showAndWait();
                }
            }
        if(tipoAlerta.equals("confirmacion")){
            if(subtipo.equals("logout")){
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Cerrar sesión");
                confirmacion.setHeaderText("Confirme cierre de sesión");
                confirmacion.setContentText("¿Está seguro que quiere cerrar su sesión y regresar a la zona de autenticación? ");

                confirmacion.getButtonTypes().clear();
                confirmacion.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

                //Deactivate Defaultbehavior for yes-Button:
                Button yesButton = (Button) confirmacion.getDialogPane().lookupButton( ButtonType.YES );
                yesButton.setDefaultButton( false );

                //Activate Defaultbehavior for no-Button:
                Button noButton = (Button) confirmacion.getDialogPane().lookupButton( ButtonType.NO );
                noButton.setDefaultButton( true );

                result = confirmacion.showAndWait();
            }
            if(subtipo.equals("alta")){
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Confirmación de Alta");
                confirmacion.setHeaderText("Confirme alta de "+encabezado);
                confirmacion.setContentText("¿Está seguro que quiere realizar el alta del "+encabezado+"?" +
                        "\nPor favor verifique los siguientes datos:\n\n" +contenido);

                confirmacion.getButtonTypes().clear();
                confirmacion.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

                //Deactivate Defaultbehavior for yes-Button:
                Button yesButton = (Button) confirmacion.getDialogPane().lookupButton( ButtonType.YES );
                yesButton.setDefaultButton( false );

                //Activate Defaultbehavior for no-Button:
                Button noButton = (Button) confirmacion.getDialogPane().lookupButton( ButtonType.NO );
                noButton.setDefaultButton( true );

                result = confirmacion.showAndWait();
            }
        }
        if(tipoAlerta.equals("aviso")) {
            if(subtipo.equals("alta")) {
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle(titulo);
                aviso.setHeaderText("**Alta de "+encabezado+" completada satisfactoriamente**");
                aviso.setContentText("El "+encabezado+"ha sido agregado exitosamente a la base de datos\n " +
                        "Los datos del espacio son los siguientes:\n"+contenido);
                result = aviso.showAndWait();
            }
        }
        return result;
    }
}