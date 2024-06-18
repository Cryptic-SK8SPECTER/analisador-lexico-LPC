/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Lexema;

/**
 *
 *  @author T490 U88
 */
public class OrganizadorDeTexto {

    static List<Lexema> lista;
    int posicaoEspecial;
    String verify;
    String simbolos = "'-+:*=><()[]=.,;..";

    public void organize(String texto) {
//a+b;
        lista = new ArrayList();
        //armazena todas a linhas do texto
        String[] textoOrg = texto.split("\n");

        /**
         * Essas variaveis dizem se ha ou nao um comentario multi-linha
         */
        boolean coment2 = false, coment3 = false, write = false, literal = false;

        /*O primeiro corre ate o fim do array que armazena os paragrafos*/
        for (int a = 0; a < textoOrg.length; a++) {
// INTEGER A = 20;
            String linha = Integer.toString(a + 1);  //linha ='1'
            String[] TexDivPEsp = textoOrg[a].split(" ");// 1= integer 2=a 3 = "=" 4= 20;
            int i = 0;
            boolean coment = false;
            /**
             * coment serve para controloar os comentarios de linha // coment2
             * serve para controlar comentarios entre (* coment 3 serve para
             * controlar comentario entre { Se tiver sido encontrado comentarios
             * do tipo coment2 ou coment3, essas variaveis terao o valor true
             */

            /*Esse ciclo corre ate o fim do array que armazena as palavras de cada paragrafos
             */
            for (; i < TexDivPEsp.length; i++) {
                /**
                 * Por exmplo, na linguagem php, depois do echo " tudo que vem
                 * depois eh considerado literal ate encontrar outra", pensando
                 * desse jeito
                 */

                if ((TexDivPEsp[i].equalsIgnoreCase("writeln") || TexDivPEsp[i].equalsIgnoreCase("write")) & !literal & !write & !coment2 & !coment3) {
                    write = true;
                    pega(TexDivPEsp[i], linha);
                } else {
                    /**
                     * Verifica se foi encontrada a palavra write ou writeln se
                     * tiver sido encontrado lexema, a variavel write tera o
                     * valor true sendo assim:
                     */
                    if (write & TexDivPEsp[i].length() >= 2 & !coment2 & !coment3) {
                        /**
                         * Para que a variavel literal tenha o valor true, ha
                         * necesidade de se econtrar o lexema (' logo depois de
                         * ter sido encontrado o lexema write ou writeln, se nao
                         * se encontra esse lexema, a variavel write tera o
                         * valor falso e o sistema fica sabendo que nao foi
                         * encontrado nenhum write
                         */
                        // WRITE ('EU ')
                        if (TexDivPEsp[i].equals("('") || TexDivPEsp[i].substring(0, 2).equals("('")) {
                            literal = true;
                        } else {
                            write = false;
                            pega(TexDivPEsp[i], linha);
                        }
                        if (TexDivPEsp[i].substring(0, 2).equals("('")) {
                            literal(linha, TexDivPEsp[i].substring(2, TexDivPEsp[i].length()));
                        } ///////
                    } else {
                        /**
                         * NEsse if, a ideia eh verificar se o valor do litera e
                         * verdadeiro ou falso, se for verdadeiro, em todos os
                         * lexemas que irao ser analisados, tem que se verificar
                         * se tem as condioes necessarias para mudaro valor
                         * logico para falso e comecar a analisar todos os
                         * lexemas sem ter em conta que eh um literal
                         */
                        if (literal & !coment2 & !coment3) {
                            if ((TexDivPEsp[i].equals("')") || TexDivPEsp[i].endsWith("')"))) {
                                literal = false;
                                write = false;
                            } else {
                                literal(TexDivPEsp[i], linha);
                            }
                            if (TexDivPEsp[i].endsWith("')")) {
                                literal(linha, TexDivPEsp[i].substring(0, TexDivPEsp[i].length() - 2));
                            }
                        } else {
                            /**
                             * Primeiro verifica se a variavel coment3 eh true
                             * ou nao. Se for, vai ser preciso verificar se a
                             * palavra que esta a ser analisada nesse exato
                             * momento tem caracteristicas para mudar o valor da
                             * variavel coment3 ou nao. Se nao for true, o
                             * lexema eh ignorado e passa-se para o seguinte.
                             */
                            if (coment3) {
                                if (TexDivPEsp[i].equals("}")) {
                                    coment3 = false;
                                } else if (TexDivPEsp[i].endsWith("}")) {
                                    coment3 = false;
                                }
                            } else {
                                /**
                                 * Mesmo raciocinio que o coment3
                                 */
                                if (coment2) {
                                    if (TexDivPEsp[i].equals("*)")) {
                                        coment2 = false;
                                    } else if (TexDivPEsp[i].endsWith("*)")) {
                                        coment2 = false;
                                    }
                                } else {
                                    /**
                                     * Ha lexemas com tamanho 1, sendo assim,nao
                                     * e possivel fazer a verificacao de
                                     * comentarios de linha por isso que para
                                     * verificar se existe comentario de linha
                                     * ou nao,preciso de uma string de tamanho
                                     * maior ou igua a 2
                                     */
                                    if (TexDivPEsp[i].length() >= 2) {
//a+b;
                                        //Fazendo a verificacao de comentario de linha
                                        if (TexDivPEsp[i].substring(0, 2).equals("//")) {
                                            coment = true;

                                            //Fazendo a verificacao de comentario de linhas multiplas    
                                        } else if (TexDivPEsp[i].substring(0, 2).equals("(*")) {
                                            coment2 = true;

                                            //Fazendo a verificacao de comentario de linhas multiplas        
                                        } else if (TexDivPEsp[i].substring(0, 1).equals("{")) {
                                            coment3 = true;

                                            //Se nao encontrou nenhum tipo de comentario, leva o lexema para ser analisado    
                                        } else if (!coment & !coment2 & !coment3) {
                                            pega(TexDivPEsp[i], linha);
                                        }
                                    } else {
                                        /**
                                         * Para fazer a verificacao desse tipo
                                         * de comentario, o tamanho do lexema
                                         * nao importa,por isso a verificacao e
                                         * feita aqui e em cima
                                         */
                                        if (TexDivPEsp[i].equals("{")) {
                                            coment3 = true;
                                        }
                                        if (!coment & !coment2 & !coment3) {
                                            pega(TexDivPEsp[i], linha); //write('ola
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        meteNatab(lista);
    }

    public void pega(String textoDividindoPorEspaco, String linha) {
        posicaoEspecial = 0;

        //write('ola
        /*Esse ciclo corre ate o fim do tamanho de cada paragrafo*/
        for (int c = 0; c < textoDividindoPorEspaco.length(); c++) {
//20;
            if (simbolos.contains(textoDividindoPorEspaco.charAt(c) + "")) {
                verify = textoDividindoPorEspaco.substring(posicaoEspecial, c);
                posicaoEspecial = c + 1;// =6

                if (!verify.isEmpty()) {
                    getToken(linha, verify);
                }
                simboloSpecial(linha, String.valueOf(textoDividindoPorEspaco.charAt(c)));

            } else if (c == textoDividindoPorEspaco.length() - 1) {
                verify = textoDividindoPorEspaco.substring(posicaoEspecial, c + 1);
                getToken(linha, verify);
            }
        }
    }

    public void getToken(String linha, String lex) {
        String Descricao = new LookForLexema().validar(lex);
        Lexema Lexema = new Lexema(linha, lex, Descricao);
        lista.add(Lexema);
    }

    public void simboloSpecial(String linha, String lex) {
        Lexema Lexema = new Lexema(linha, lex, "Simbolo especial");
        lista.add(Lexema);
    }

    public void literal(String linha, String lex) {
        Lexema Lexema = new Lexema(linha, lex, "Literal");
        lista.add(Lexema);
    }

    public void meteNatab(List<Lexema>lista) {
        new Tabela().meteNaTab(lista);
    }
  
   
}
