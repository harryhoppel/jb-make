package com.jetbrains.jbmake.core.editor;

/**
 * @author daywalker
 * @since 30/06/15.
 */
public class VariableFinder {
    public String getVariableName(String fileContent, int caretPosition) {
        // should replace it with a visitor with all these nitty-gritty conversion between absolute index and locations
        int nameStart = calculateNameStart(caretPosition, fileContent);
        int nameEnd = calculateNameEnd(caretPosition, fileContent);
        return fileContent.substring(nameStart, nameEnd);
    }

    private int calculateNameStart(int caretPosition, String fileContent) {
        int nameStart = 0;
        for (int i = caretPosition; i >= 0; i--) {
            char nextChar = fileContent.charAt(i);
            if (!Character.isJavaIdentifierPart(nextChar) && nextChar != '.') {
                nameStart = i + 1;
                break;
            }
        }
        return nameStart;
    }

    private int calculateNameEnd(int caretPosition, String fileContent) {
        int nameEnd = fileContent.length();
        for (int i = caretPosition; i < fileContent.length(); i++) {
            char nextChar = fileContent.charAt(i);
            if (!Character.isJavaIdentifierPart(nextChar) && nextChar != '.') {
                nameEnd = i;
                break;
            }
        }
        return nameEnd;
    }


}
