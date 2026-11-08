package br.com.gattigit.MinecraftClone.models;

public class Triangulo implements IFormato{

    private static final float[] vertices = {
     0.5f,  0.5f, 0.0f,
    -0.5f, -0.5f, 0.0f,
     0.5f, -0.5f, 0.0f
    };

    private static final int[] indices = {
    0, 1, 2//x, y e z
    };

    private float[] posA;

    private float[] matrizModel = {
          1.0f, 0.0f, 0.0f, 0.0f
        , 0.0f, 1.0f, 0.0f, 0.0f
        , 0.0f, 0.0f, 1.0f, 0.0f
        , 0.0f, 0.0f, 0.0f, 1.0f 
    };

    public void MovedMatriz(Double a[], Double e[], Double v[]){
        /*a = alpha, e = ampliacao, v = translacao */
        double ax = Math.toRadians(a[0]);
        double ay = Math.toRadians(a[1]);
        double az = Math.toRadians(a[2]);

        float[] t = new float[16];
        //#region definicao da matriz
        /*------------------------ */
        t[0] = f(cos(az)*cos(ay)*e[0]);
        t[1] = f((-sen(az)*cos(ax) + cos(az)*sen(ay)*sen(ax))*e[1]);
        t[2] = f((sen(az)*sen(ax) + cos(az)*sen(ay)*cos(ax))*e[2]);
        t[3] = f(v[0]);
        /*------------------------ */
        t[4] = f(sen(az)*cos(ay)*e[0]);
        t[5] = f((cos(az)*cos(ax) + sen(az)*sen(ay)*sen(ax))*e[1]);
        t[6] = f((-cos(az)*sen(ax) + sen(az)*sen(ay)*cos(ax))*e[2]);
        t[7] = f(v[1]);
        /*------------------------ */
        t[8] = f(-sen(ay)*e[0]);
        t[9] = f(cos(ay)*sen(ax)*e[1]);
        t[10] = f(cos(ay)*cos(ax)*e[2]);
        t[11] = f(v[2]);
        /*------------------------ */
        t[12] = f(0);
        t[13] = f(0);
        t[14] = f(0);
        t[15] = f(1);
        /*------------------------ */
        //#endregion

        matrizModel = new float[]{
              t[0], t[1], t[2], t[3]
            , t[4], t[5], t[6], t[7]
            , t[8], t[9], t[10], t[11]
            , t[12], t[13], t[14], t[15]
        };
    }

    //Construtor 
    public Triangulo(){
        posA = vertices.clone();
    }
    
    //#region Getters
    public float[] getMatrizModel(){
        return matrizModel;
    }


    @Override
    public float[] getVertices() {
        return posA;
    }

    @Override
    public int[] getIndices() {
        return indices;
    }
    //#endregion

    /*deprecated */
    @Override
    public void translacao(Float[] pos) {
        int contador = 0;
        for (int i = 0; i < posA.length; i++) {
            System.out.println(i);
            posA[i] += pos[contador];
            contador = (contador + 1) % 3; 
        }
    }

    //#region Otimizações
    private static double sen(double alpha){
        return Math.sin(alpha);
    }

    private static double cos(double alpha){
        return Math.cos(alpha);
    }

    private static float f(double n){
        return (float)n;
    }
    //#endregion
}
