JAVAC = javac

JAVA = java

SRC_DIR = .

BIN_DIR = bin

MAIN_CLASS = Main



.PHONY: clean all run


# Archivos fuente

SOURCES = $(wildcard $(SRC_DIR)/*.java)

# Archivos compilados

CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

all: $(BIN_DIR)/$(MAIN_CLASS).class



# Regla para compilar archivos .java a .class

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java

@mkdir -p $(BIN_DIR)

$(JAVAC) -d $(BIN_DIR) $<


run: all

$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

clean:

rm -rf $(BIN_DIR)
