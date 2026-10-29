package br.com.gattigit.MinecraftClone.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.springframework.stereotype.Service;

import br.com.gattigit.MinecraftClone.models.IFormato;
//import br.com.gattigit.MinecraftClone.models.RObjetoRenderizavel;
import br.com.gattigit.MinecraftClone.models.Triangulo;

@Service
public class Render {

    private List<Float> objetosParaRederizar_vertices = new ArrayList<>();

    private List<Integer> objetosParaRederizar_indices = new ArrayList<>();

    private Map<List<Float>, Integer> verticesExistentes = new HashMap<>();

    private int indicesExistente = 0;

    private int VAO;

    private int VBO;

    private int EBO;

    private int shaderProgram;

    //shader pra gerar o formato
    private String vertexSource = """
        #version 330 core
        layout (location = 0) in vec3 aPos;

        void main() {
            gl_Position = vec4(aPos, 1.0);
        }
    """;

    //shader pra pintar o formato
    private String fragmentSource = """
        #version 330 core
        out vec4 FragColor;

        void main() {
            FragColor = vec4(0.0, 0.0, 1.0, 1.0);
        }
    """;

    public void init(){
        //vertices
        VAO = GL30.glGenVertexArrays();//vertex array obj : como ler e oq é cada dado
        GL30.glBindVertexArray(VAO);
        
        VBO = GL15.glGenBuffers();//vertex buffer obj : dados
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, new Triangulo().getVertices(), GL15.GL_STATIC_DRAW);

        //ordem
        EBO = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, EBO);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER,new Triangulo().getIndices(),
            GL15.GL_STATIC_DRAW);

        //configs
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 3 * Float.BYTES, 0L);
        GL20.glEnableVertexAttribArray(0);

        /****  SHADER  ****/
        //formato do objeto
        int vertexShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexShader, vertexSource);
        GL20.glCompileShader(vertexShader);

        //checa se funcionou
        int sucessoVertex = GL20.glGetShaderi(vertexShader, GL20.GL_COMPILE_STATUS);
        if(sucessoVertex == GL11.GL_FALSE) throw new RuntimeException("Erro no Vertex Shader:\n"+
            GL20.glGetShaderInfoLog(vertexShader));

        //cor do objeto
        int fragmentShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShader, fragmentSource);
        GL20.glCompileShader(fragmentShader);

        //checa se funcionou
        int sucessoFragment = GL20.glGetShaderi(fragmentShader, GL20.GL_COMPILE_STATUS);
        if(sucessoFragment == GL20.GL_FALSE) throw new RuntimeException("Erro no Fragment Shader:\n"+
            GL20.glGetShaderInfoLog(fragmentShader));

        //SHADER FINAL
        shaderProgram = GL20.glCreateProgram();//reserva
        GL20.glAttachShader(shaderProgram, vertexShader);//adicion
        GL20.glAttachShader(shaderProgram, fragmentShader);//adiciona
        GL20.glLinkProgram(shaderProgram);//prepara para ser usado

        //checa se funcionou
        int sucessoLink = GL20.glGetProgrami(shaderProgram, GL20.GL_LINK_STATUS);
        if(sucessoLink == GL20.GL_FALSE) throw new RuntimeException("Erro ao linkar o shader Program:\n"+
            GL20.glGetProgramInfoLog(shaderProgram));

        //deleta os shaders sem proposito atual
        GL20.glDeleteShader(vertexShader);
        GL20.glDeleteShader(fragmentShader);
    }

    public void draw(){
        //shader utilizado
        GL20.glUseProgram(shaderProgram);

        //chama o vao com o ebo linkado
        GL30.glBindVertexArray(VAO);

        //finalmente desenha o objeto
        GL11.glDrawElements(GL11.GL_TRIANGLES, 3, GL11.GL_UNSIGNED_INT, 0L);
    }

    public void addObjeto(IFormato objeto){
        int contador = 0;
        List<Float> listaTemporaria = new ArrayList<>();
        for (float valor : objeto.getVertices()) {
            listaTemporaria.add(valor);
            contador++;
            //cria lista com 3 valores
            if(contador == 3){
                //checa se ja existe no map, pra poder reaproveitar no indice
                //se nao exist adiciona no map, e nas listas de vertices totais e indices totais
                if(!verticesExistentes.containsKey(listaTemporaria)){
                    listaTemporaria.forEach(objetosParaRederizar_vertices::add);
                    verticesExistentes.put(new ArrayList<>(listaTemporaria), indicesExistente);
                    objetosParaRederizar_indices.add(verticesExistentes.get(listaTemporaria));
                    indicesExistente++;
                }else{//se existe so reaproveita do hash
                    objetosParaRederizar_indices.add(verticesExistentes.get(listaTemporaria));
                }
                listaTemporaria.clear();
                contador = 0;
                //zera e limpa
            }
        }
    }
}

/*
1 - Feito shader
2 - multiplicidade de objetos
3 - reutilização de variaveis

*/
