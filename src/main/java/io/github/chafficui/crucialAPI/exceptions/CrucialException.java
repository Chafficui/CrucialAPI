package io.github.chafficui.crucialAPI.exceptions;

public class CrucialException extends Exception{

    public CrucialException(int errorCode){
        super(pickErrorMessage(errorCode));
    }

    private static String pickErrorMessage(int errorCode){
        String errorMessage;
        switch (errorCode){
            case 1:
                errorMessage = "Error 001: Invalid server version.";
                break;
            case 2:
                errorMessage = "Error 002: Could not create custom item.";
                break;
            case 4:
                errorMessage = "Error 004: Create a metrics before submitting stats.";
                break;
            case 7:
                errorMessage = "Error 007: A custom item with this id already exists!";
                break;
            case 8:
                errorMessage = "Error 008: Updater tried to download the update, but was unsuccessful.";
                break;
            case 9:
                errorMessage = "Error 009: Update check failed.";
                break;
            case 10:
                errorMessage = "Error 010: Could not save options.yml";
                break;
            case 28:
                errorMessage = "Error 028: Failed to download legacy version.";
                break;
            case 29:
                errorMessage = "Error 029: Could not register custom item.";
                break;
            default:
                errorMessage = "Error 999: An unknown error occurred.";
                break;
        }
        return errorMessage;
    }
}
