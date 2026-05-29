package fatefranca.produto.service;

import fatefranca.produto.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    //criar lista de produtos
    private List<Produto> lista =  new ArrayList<Produto>();
    private Long nextId = 1L;
    public ProdutoService() {}
    public List<Produto> listar() {
        return this.lista;
    }

    public Produto criar(Produto p){
        // cria produto
        p.setId(nextId); // insere id
        nextId++; //atualiza id
        this.lista.add(p); // adiciona na lista
        return p; // retorna produto
    }

    public boolean remover(Long id){
        // remove o produto atravez/utilizando  ID
        for(int i=0;i<this.lista.size();i++){
            if(lista.get(i).getId().equals(id)){ // usar equals quando comparar 2 objetos
                this.lista.remove(i);
                return true;
            }
        }
        return false;
    }

    public  Produto atualizar(Long id, Produto novo){
        // atualiza produto
        for (int i = 0; i < this.lista.size(); i++) {
            if (lista.get(i).getId().equals(id)) {
                novo.setId(id);
                lista.set(i, novo);
                return novo; // atualizou
            }
        }
        return null; // não atualizou
    }
}
