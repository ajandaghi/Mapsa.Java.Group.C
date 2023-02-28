package ir.mapsa.spring.java.ClassTodo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public abstract class AbstractController<E extends  Student> {


    public Repository<Student> repository=new Repository<>(Student.class);

    @PostMapping("")
    public void add(@RequestBody E e) throws Exception {
        repository.add(e);
    }

    @PutMapping()
    public void update(@RequestBody E e) throws Exception {
        repository.update(e);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) throws Exception {
        repository.removeById(id);

    }

    @GetMapping("/{id}")
    public E findById(@PathVariable("id") Long id) throws Exception {
        return (E) repository.findById(id);
    }

    @GetMapping()
    public List<Student> getAll() throws Exception {
        return repository.getAll();
    }

    @PostMapping("/getExm")
    public List<Student> getExample(@RequestBody E e) throws Exception {
        return repository.findByExample(e);
    }
}
