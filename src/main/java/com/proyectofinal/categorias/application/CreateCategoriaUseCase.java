package com.proyectofinal.categorias.application;

import com.proyectofinal.categorias.domain.entity.Categoria;
import com.proyectofinal.categorias.domain.service.CategoriaService;

public class CreateCategoriaUseCase {
    private final CategoriaService categoriaService;

    public CreateCategoriaUseCase(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public void execute(Categoria categoria) {
        categoriaService.createCategoria(categoria);
    }
}
