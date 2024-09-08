package com.proyectofinal.categorias.application;

import com.proyectofinal.categorias.domain.entity.Categoria;
import com.proyectofinal.categorias.domain.service.CategoriaService;

public class UpdateCategoriaUseCase {
    private final CategoriaService categoriaService;

    public UpdateCategoriaUseCase(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public void execute(Categoria categoria) {
        categoriaService.updateCategoria(categoria);
    }
}
