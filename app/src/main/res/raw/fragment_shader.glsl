precision mediump float;

uniform sampler2D u_Texture; // Textura
uniform vec3 u_LightDirection; // Direção da luz
uniform vec4 u_LightColor; // Cor da luz

varying vec2 v_TexCoord;
varying vec3 v_Normal;

void main() {
    vec3 normal = normalize(v_Normal);
    float lightFactor = max(dot(normal, normalize(u_LightDirection)), 0.0);
    vec4 textureColor = texture2D(u_Texture, v_TexCoord);
    vec4 finalColor = textureColor * u_LightColor * lightFactor;
    gl_FragColor = finalColor;
}
