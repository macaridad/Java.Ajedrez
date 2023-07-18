package ar.edu.utn.frc.tup.lciii;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ArchivoTest {

    @Test
    public void testRead() {
        Archivo archivo = new Archivo();

        // Configurar el nombre del archivo de prueba
        String fileName = "prueba.txt";

        // Crear un archivo de prueba con contenido
        createTestFile(fileName, "Línea 1\nLínea 2\nLínea 3");

        // Leer el archivo utilizando el método read() de la clase Archivo
        String result = archivo.read(fileName);

        // Verificar que el contenido leído sea el esperado
        assertEquals("Línea 1\nLínea 2\nLínea 3\n", result);

        // Eliminar el archivo de prueba
        deleteTestFile(fileName);
    }

    private void createTestFile(String fileName, String content) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            fail("Error al crear el archivo de prueba: " + e.getMessage());
        }
    }

    private void deleteTestFile(String fileName) {
        File file = new File(fileName);
        if (!file.delete()) {
            fail("Error al eliminar el archivo de prueba");
        }
    }
}