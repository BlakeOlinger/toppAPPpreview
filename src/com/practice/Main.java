package com.practice;

/*
This microservice is responsible for the SW microservice local
TOPP App preview for Java base TOPP App implementations.

This is a 'dumb' microservice in that it won't be generating any data
save for user selection if a user has selected a feature in the preview
window, then that information will be sent to the GUI to reflect that
selection.

 */

import com.lib.*;

import java.nio.file.Files;
import java.nio.file.Paths;

final class Main {

    public static void main(String[] args) {
        System.out.println("TOPP App - Configure Preview - Start");

        var installRoot = InstallRoot.getInstallRoot("SolidWorks Daemon");

        var installDirectoryPath = Paths.get(installRoot);

        if (!ToppFiles.validateDirectory(
                "Install",
                installDirectoryPath))
            return;

        var DBdaemonFileName = "toppAppDBdaemon.jar";

        PathsList.DBdaemon = Paths.get(installRoot + DBdaemonFileName);

        if (!Files.exists(PathsList.DBdaemon))
            if (!BlobDirectory.validateLocalBlobDatabaseInstance(installDirectoryPath))
                return;

        var configFileName = "toppAppPreview.config";

        PathsList.previewConfig = Paths.get(installRoot + configFileName);

        if (ToppFiles.validateFile(configFileName, PathsList.previewConfig))
            return;

        if (ToppFiles.writeFile(configFileName, PathsList.previewConfig, Commands.PROGRAM_INIT))
            return;

        System.out.println("TOPP App - Configure Preview - Exit");
    }
}
