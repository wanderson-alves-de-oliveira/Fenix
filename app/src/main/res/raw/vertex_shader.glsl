attribute vec4 a_Position;  // Posição do vértice
attribute vec2 a_TexCoord;  // Coordenadas de textura
attribute vec3 a_Normal;    // Vetor normal

uniform mat4 u_MVPMatrix;   // Matriz de projeção/modelo/visão
uniform mat4 u_NormalMatrix; // Matriz para transformar normais

varying vec2 v_TexCoord;
varying vec3 v_Normal;

void main() {
    gl_Position = u_MVPMatrix * a_Position;
    v_TexCoord = a_TexCoord;
    v_Normal = normalize(vec3(u_NormalMatrix * vec4(a_Normal, 0.0)));
}
