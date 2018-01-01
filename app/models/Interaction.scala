package models

trait Interaction[I, O] {
  def handle(request : I) : O
}