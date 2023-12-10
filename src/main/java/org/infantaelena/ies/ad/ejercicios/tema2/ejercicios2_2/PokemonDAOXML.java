package org.infantaelena.ies.ad.ejercicios.tema2.ejercicios2_2;

import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.Pokemon;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.PokemonDAO;
import org.infantaelena.ies.ad.ejercicios.tema1.ejercicios1_6.dao.pokemon.excepciones.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAOXML implements PokemonDAO {

    private final String filePath;

    public PokemonDAOXML(String filePath) {
        this.filePath = filePath;
    }

    private Document getDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(filePath));
    }

    private void writeDocument(Document doc) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }

    @Override
    public boolean estaVacio() throws DataAccessException {
        try {
            Document doc = getDocument();
            NodeList nodeList = doc.getElementsByTagName("Pokemon");
            return nodeList.getLength() == 0;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public boolean estaLLeno() throws DataAccessException {
        try {
            Document doc = getDocument();
            NodeList nodeList = doc.getElementsByTagName("Pokemon");
            // Puedes definir un límite de capacidad y compararlo con la longitud de la lista.
            int capacityLimit = 100; // Ejemplo de límite de capacidad
            return nodeList.getLength() >= capacityLimit;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void aniadir(Pokemon pokemon) throws DataAccessException, DataDestFullException, DuplicateKeyException {
        try {
            Document doc = getDocument();
            Element root = doc.getDocumentElement();
            NodeList nodeList = doc.getElementsByTagName("Pokemon");

            // Verificar si ya existe un Pokemon con el mismo nombre
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getElementsByTagName("name").item(0).getTextContent().equals(pokemon.getName())) {
                        throw new DuplicateKeyException("Ya existe un Pokemon con el mismo nombre: " + pokemon.getName());
                    }
                }
            }

            // Crear un nuevo nodo Pokemon y agregarlo al documento
            Element pokemonElement = doc.createElement("Pokemon");

            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(pokemon.getName()));
            pokemonElement.appendChild(nameElement);

            Element levelElement = doc.createElement("level");
            levelElement.appendChild(doc.createTextNode(String.valueOf(pokemon.getLevel())));
            pokemonElement.appendChild(levelElement);

            Element healthElement = doc.createElement("health");
            healthElement.appendChild(doc.createTextNode(String.valueOf(pokemon.getHealth())));
            pokemonElement.appendChild(healthElement);

            Element attackElement = doc.createElement("attack");
            attackElement.appendChild(doc.createTextNode(String.valueOf(pokemon.getAttack())));
            pokemonElement.appendChild(attackElement);

            Element defenseElement = doc.createElement("defense");
            defenseElement.appendChild(doc.createTextNode(String.valueOf(pokemon.getDefense())));
            pokemonElement.appendChild(defenseElement);

            Element specialAttackElement = doc.createElement("specialAttack");
            specialAttackElement.appendChild(doc.createTextNode(String.valueOf(pokemon.getSpecialAttack())));
            pokemonElement.appendChild(specialAttackElement);

            Element specialDefenseElement = doc.createElement("specialDefense");
            specialDefenseElement.appendChild(doc.createTextNode(String.valueOf(pokemon.getSpecialDefense())));
            pokemonElement.appendChild(specialDefenseElement);

            Element speedElement = doc.createElement("speed");
            speedElement.appendChild(doc.createTextNode(String.valueOf(pokemon.getSpeed())));
            pokemonElement.appendChild(speedElement);

            root.appendChild(pokemonElement);
            writeDocument(doc);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public boolean eliminar(Pokemon pokemon) throws DataAccessException, DataIntegrityException {
        try {
            Document doc = getDocument();
            Element root = doc.getDocumentElement();
            NodeList nodeList = doc.getElementsByTagName("Pokemon");

            // Buscar el nodo Pokemon correspondiente al objeto Pokemon dado
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getElementsByTagName("name").item(0).getTextContent().equals(pokemon.getName())) {
                        root.removeChild(node);
                        writeDocument(doc);
                        return true; // Devuelve true si se elimina con éxito
                    }
                }
            }
            return false; // Devuelve false si no se encuentra el Pokemon a eliminar
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Pokemon> leerPokemons() throws DataAccessException, IncompatibleVersionException {
        try {
            List<Pokemon> pokemons = new ArrayList<>();
            Document doc = getDocument();
            NodeList nodeList = doc.getElementsByTagName("Pokemon");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    int level = Integer.parseInt(element.getElementsByTagName("level").item(0).getTextContent());
                    int health = Integer.parseInt(element.getElementsByTagName("health").item(0).getTextContent());
                    int attack = Integer.parseInt(element.getElementsByTagName("attack").item(0).getTextContent());
                    int defense = Integer.parseInt(element.getElementsByTagName("defense").item(0).getTextContent());
                    int specialAttack = Integer.parseInt(element.getElementsByTagName("specialAttack").item(0).getTextContent());
                    int specialDefense = Integer.parseInt(element.getElementsByTagName("specialDefense").item(0).getTextContent());
                    int speed = Integer.parseInt(element.getElementsByTagName("speed").item(0).getTextContent());

                    Pokemon pokemon = new Pokemon(name, level, health, attack, defense, specialAttack, specialDefense, speed);
                    pokemons.add(pokemon);
                }
            }
            return pokemons;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Pokemon> leerPokemons(String name) throws DataAccessException, IncompatibleVersionException {
        try {
            List<Pokemon> pokemons = new ArrayList<>();
            Document doc = getDocument();
            NodeList nodeList = doc.getElementsByTagName("Pokemon");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String pokemonName = element.getElementsByTagName("name").item(0).getTextContent();
                    if (pokemonName.toLowerCase().contains(name.toLowerCase())) {
                        int level = Integer.parseInt(element.getElementsByTagName("level").item(0).getTextContent());
                        int health = Integer.parseInt(element.getElementsByTagName("health").item(0).getTextContent());
                        int attack = Integer.parseInt(element.getElementsByTagName("attack").item(0).getTextContent());
                        int defense = Integer.parseInt(element.getElementsByTagName("defense").item(0).getTextContent());
                        int specialAttack = Integer.parseInt(element.getElementsByTagName("specialAttack").item(0).getTextContent());
                        int specialDefense = Integer.parseInt(element.getElementsByTagName("specialDefense").item(0).getTextContent());
                        int speed = Integer.parseInt(element.getElementsByTagName("speed").item(0).getTextContent());

                        Pokemon pokemon = new Pokemon(pokemonName, level, health, attack, defense, specialAttack, specialDefense, speed);
                        pokemons.add(pokemon);
                    }
                }
            }
            return pokemons;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void actualizar(Pokemon p) throws DataAccessException, IncompatibleVersionException {
        try {
            Document doc = getDocument();
            NodeList nodeList = doc.getElementsByTagName("Pokemon");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getElementsByTagName("name").item(0).getTextContent().equals(p.getName())) {
                        element.getElementsByTagName("level").item(0).setTextContent(String.valueOf(p.getLevel()));
                        element.getElementsByTagName("health").item(0).setTextContent(String.valueOf(p.getHealth()));
                        element.getElementsByTagName("attack").item(0).setTextContent(String.valueOf(p.getAttack()));
                        element.getElementsByTagName("defense").item(0).setTextContent(String.valueOf(p.getDefense()));
                        element.getElementsByTagName("specialAttack").item(0).setTextContent(String.valueOf(p.getSpecialAttack()));
                        element.getElementsByTagName("specialDefense").item(0).setTextContent(String.valueOf(p.getSpecialDefense()));
                        element.getElementsByTagName("speed").item(0).setTextContent(String.valueOf(p.getSpeed()));
                    }
                }
            }
            writeDocument(doc);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void pokemonCSV(String ruta, String name, int level, int life, int atack, int defense, int specialAttack, int specialdefense, int speed) {
    }

    @Override
    public void imprimirPokemonCSV(String ruta) throws NoSuchFileException {
    }

    @Override
    public void imprimirPokemon(String nombre) {
        try {
            Document doc = getDocument();
            NodeList nodeList = doc.getElementsByTagName("Pokemon");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String pokemonName = element.getElementsByTagName("name").item(0).getTextContent();
                    if (pokemonName.toLowerCase().contains(nombre.toLowerCase())) {
                        int level = Integer.parseInt(element.getElementsByTagName("level").item(0).getTextContent());
                        int health = Integer.parseInt(element.getElementsByTagName("health").item(0).getTextContent());
                        int attack = Integer.parseInt(element.getElementsByTagName("attack").item(0).getTextContent());
                        int defense = Integer.parseInt(element.getElementsByTagName("defense").item(0).getTextContent());
                        int specialAttack = Integer.parseInt(element.getElementsByTagName("specialAttack").item(0).getTextContent());
                        int specialDefense = Integer.parseInt(element.getElementsByTagName("specialDefense").item(0).getTextContent());
                        int speed = Integer.parseInt(element.getElementsByTagName("speed").item(0).getTextContent());

                        System.out.println("Name: " + pokemonName);
                        System.out.println("Level: " + level);
                        System.out.println("HP: " + health);
                        System.out.println("Attack: " + attack);
                        System.out.println("Defense: " + defense);
                        System.out.println("Special attack: " + specialAttack);
                        System.out.println("Special defense: " + specialDefense);
                        System.out.println("Speed: " + speed);
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

