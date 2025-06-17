JAVAC = javac
JAVA = java

SRC_DIR = .
BIN_DIR = bin
MAIN_CLASS_1 = Main
MAIN_CLASS_2 = MainGUI

.PHONY: clean all run

# Encuentra todos los .java en SRC_DIR y subdirectorios
SOURCES := $(shell find $(SRC_DIR) -name "*.java")

# Crea la estructura de .class correspondientes en BIN_DIR
CLASSES := $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

all: $(CLASSES)

# Compilar todos los .java en bin usando -d para rutas
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@)
	$(JAVAC) -d $(BIN_DIR) $<

run1: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS_1)

run2: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS_2)

clean:
	find . -name "*.class" -type f -delete
