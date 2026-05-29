package fatefranca.produto.Controller;

import fatefranca.produto.model.Produto;
import fatefranca.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired //injeção de dependencia
    // podemos usar service sem instancia-lo
    private ProdutoService service;

    @GetMapping // verbo get
    public ResponseEntity<List<Produto>> listar(){
        //status da resposta é ok (sucesso)
        return ResponseEntity.ok(service.listar());
    }
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto p){
        // chama garçom
        Produto criado  =  service.criar(p);
        // cria uma uri com o id do produto criado
        // URL - Uniform Resource Locator
        // URI - Uniform Resource Identifier
        URI uri = URI.create("/produtos/" + criado.getId());
        // retorna status de sucesso 201 (created)
        return ResponseEntity.created(uri).body(criado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        return service.remover(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto p){
        Produto ret = service.atualizar(id, p);
        if(ret == null){
            // não encontrou para atualizar
            return ResponseEntity.notFound().build();
        }
        else  {
            // retorna produto atualizado
            return ResponseEntity.ok(ret);
        }
    }
}
